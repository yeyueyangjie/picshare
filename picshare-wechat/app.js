App({
  onLaunch: function() {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  // 注册（启动）
  SignUp: function(userinfo) {
    let that = this
    // 微信登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId
        wx.request({
          url: that.globalData.serviceurl + '/user/wxlogin',
          data: {
            code: res.code
          },
          method: 'GET',
          header: {
            'content-type': 'application/json'
          },
          success(res) {
            let openid = res.data.data.list[0].openid
            // 判断用户是否存在
            wx.request({
              url: that.globalData.serviceurl + '/user/detail',
              data: {
                userId: res.data.data.list[0].openid
              },
              method: 'GET',
              header: {
                'content-type': 'application/json'
              },
              // 用户个人信息调用接口 
              // 调用成功
              // 存在:读入缓存,跳转index 
              // 不存在:授权登录,存入缓存
              success(res) {
                if (res.data.code == 200) {
                  wx.showToast({
                    title: '登录成功',
                    icon: 'none'
                  })
                  //存入缓存和全局变量
                  wx.setStorageSync("user", res.data.data.list[0])
                  that.globalData.userId = res.data.data.list[0].userId
                  that.globalData.userInfo = res.data.data.list[0]
                  //跳转index
                  wx.reLaunch({
                    url: '/pages/index/index',
                  })
                } else if (res.data.code == 302) {
                  // 该异常为查找之后不存在用户
                  wx.request({
                    url: that.globalData.serviceurl + '/user/add',
                    data: {
                      'userId': openid,
                      'userName': userinfo.detail.userInfo['nickName'],
                      'sex': userinfo.detail.userInfo['gender'],
                      'address': userinfo.detail.userInfo['country'] + userinfo.detail.userInfo['province'] + userinfo.detail.userInfo['city'],
                      'portrait': userinfo.detail.userInfo['avatarUrl']
                    },
                    method: 'POST',
                    header: {
                      'content-type': 'application/json'
                    },
                    success(res) {
                      // 新建用户
                      if (res.data.code === 200) {
                        wx.showToast({
                          title: '登录成功',
                          icon: 'none'
                        })
                        // 缓存,跳转
                        wx.setStorageSync("user", res.data.data.list[0])
                        that.globalData.userId = res.data.data.list[0].userId
                        that.globalData.userInfo = res.data.data.list[0]
                        wx.reLaunch({
                          url: '/pages/index/index',
                        })
                      } else {
                        wx.showToast({
                          title: '登录失败',
                          icon: 'none'
                        })
                      }
                    }
                  })
                }
              }
            })
          }
        })
      }
    })
  },
  globalData: {
    userInfo: {},
    userId: '',
    location: {},
    picList: [],
    step: 0,
    // serviceurl: 'http://127.0.0.1:9000',
    serviceurl: 'http://39.96.170.153:9000'
  },
  // 登录
  SignIn: function() {
    let that = this
    try {
      const info = wx.getStorageSync('user')
      //存在缓存
      if (info) {
        that.globalData.userId = info.userId
        that.globalData.userInfo = info
        return info
      } else {
        //不存在缓存
        wx.reLaunch({
          url: '/pages/login/login'
        })
        return false
      }
    } catch (e) {
      console.log('登录异常')
      wx.reLaunch({
        url: '/pages/login/login'
      })
      return false
    }
  },
  // 获取信息
  loadPicList: function() {
    let that = this
    // 对象为空的最新ES6判断函数
    let test = (obj) => {
      let flag = Object.keys(obj)
      return (flag.length === 0)
    }
    // 第一次打开index 需要获取地理位置
    if (test(that.globalData.location)) {
      // 获取当前位置信息
      wx.chooseLocation({
        success: function (res) {
          console.log(res)
          that.globalData.location = res
        }
      })
    }
    wx.request({
      url: that.globalData.serviceurl + '/pic/list',
      data: {
        address: that.globalData.location.address + that.globalData.location.name
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          console.log('列表获取成功')
          that.globalData.picList = res.data.data.list
        }
      }
    })
  }
})