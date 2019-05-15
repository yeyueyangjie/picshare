//app.js
App({
  onLaunch: function() {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  // 根据openID 获取用户信息
  getUser: function(openid) {
    let that = this
    // 授权过的用户获取用户信息存到userinfo
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.request({
            url: that.globalData.serviceurl + '/user/detail',
            data: {
              userId: openid
            },
            method: 'GET',
            header: {
              'content-type': 'application/json'
            },
            // 用户信息接口调用成功
            // 如果用户存在读入缓存 不存在 进行登录
            success(res) {
              console.log(res)
              return res.data.data
            },
            fail(res) {
              console.log(res)
              return {
                code: '400',
                message: 'failed'
              }
            }
          })
        }
      }
    })
  },
  // 打开 小程序进行登录
  isLogin: function(userinfo) {
    let that = this
    // 微信登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId
        let code = res.code
        if (code) {
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
              console.log(res)
              let openid = res.data.data.list[0].openid
              console.log('islogin 登录界面  获取openid')
              console.log(openid)
              // 判断用户是否存在
              wx.request({
                url: that.globalData.serviceurl + '/user/detail',
                data: {
                  userId: openid
                },
                method: 'GET',
                header: {
                  'content-type': 'application/json'
                },
                // 用户信息接口调用成功
                // 如果用户存在读入缓存 不存在 进行登录
                success(res) {
                  console.log(res)
                  if (res.data.code == 200) {
                    // 信息提示 
                    wx.showToast({
                      title: '登录成功',
                      icon: 'none',
                      duration: 1000
                    })
                    // 
                    console.log('将信息缓存')
                    // 缓存
                    wx.setStorageSync("user", res.data.data.list[0])
                    that.globalData.userId = res.data.data.list[0].userId
                    that.globalData.userInfo = res.data.data.list[0]

                    wx.reLaunch({
                      url: '/pages/index/index',
                    })

                  } else if (res.data.code == 302) {
                    // 用户信息表全部获取  新建用户
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
                        console.log('新建用户成功！！')
                        console.log(res)
                        // 新建用户成功
                        if (res.data.code === 200) {
                          // 信息提示 
                          wx.showToast({
                            title: '登录成功',
                            icon: 'none',
                            duration: 1000
                          })
                          // 缓存
                          wx.setStorageSync("user", res.data.data.list[0])

                          console.log('再次跳转index')
                          wx.reLaunch({
                            url: '/pages/index/index',
                          })

                        } else {
                          wx.showToast({
                            title: '登录失败',
                            icon: 'none',
                            duration: 1000
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
      }
    })
  },
  globalData: {
    userInfo: {},
    userId: '',
    location: {},
    picList:[],
    step:0,
    serviceurl: 'http://127.0.0.1:9000',
    // serviceurl: 'http://39.96.170.153:9000'
  },
  onLogin: function() {
    let that = this
    try {
      const info = wx.getStorageSync('user')
      console.log(info)
      if (info) {
        // Do something with return value
        console.log('用户信息已存在缓存')
        that.globalData.userId = info.userId
        that.globalData.userInfo = info
        return info
      } else {
        console.log('用户信息不存在')
        wx.redirectTo({
          url: '/pages/login/login'
        })
        return false
      }
    } catch (e) {
      // Do something when catch error
      console.log('登录异常')
      wx.redirectTo({
        url: '/pages/login/login'
      })
      return false
    }
  },
  // 获取信息
  loadPicList: function () {
    let that = this
    wx.request({
      url: that.globalData.serviceurl + '/pic/list',
      data: {

      },
      method: 'GET',
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
  },
  onShow: function(e){
    this.loadPicList()
  }
})