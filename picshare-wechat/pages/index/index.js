let start
const app = getApp()
Page({
  data: {
    title: '欢迎进入你的印象',
    userId: '',
    userInfo: {},
    //需要展示的信息
    picShareId: '',
    target: '',
    title: '',
    summary: '',
    author: '',
    created: '',
    address: '',
    authorportrait: '',
    // 当前条数
    collect: 0,
    collectShow: 0,
    collectionId: ''

  },
  // 获取当前的pic
  loadPic: function(step) {
    console.log('第' + step + '条数据导入')
    let that = this
    let pic = app.globalData.picList[step]
    let picshare = pic.picShareId

    that.setData({
      picShareId: picshare,
      target: pic.target,
      title: pic.title,
      summary: pic.summary,
      author: pic.author,
      created: pic.created,
      address: pic.address,
      authorportrait: pic.authorportrait
    })

    //获取当前图片点赞条数
    wx.request({
      url: app.globalData.serviceurl + '/collection/count',
      data: {
        picShareId: picshare,
        userId: app.globalData.userId
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        console.log(res)
        if (res.data.code == 200) {
          that.setData({
            collect: res.data.data.count
          })
          if (res.data.data.list.length == 0) {
            //未收藏
            wx.request({
              url: app.globalData.serviceurl + '/collection/add',
              method: 'POST',
              data: {
                collected: app.globalData.userId,
                picShare: picshare
              },
              header: {
                'content-type': 'application/json'
              },
              success: function(res) {
                console.log(res)
                if (res.data.code == 200) {
                  //添加一条未收藏的收藏记录
                  console.log('记录创建成功')
                  that.setData({
                    collectShow: 2,
                    collectionId: res.data.data.list[0].collectionId
                  })
                }
              }
            })
          } else {
            //有收藏记录
            if (res.data.data.list[0].status == 1) {
              //收藏
              console.log('已经收藏过了')
              that.setData({
                collectShow: 1,
                collectionId: res.data.data.list[0].collectionId
              })
            } else {
              //取消收藏
              console.log('可能取消了收藏')
              that.setData({
                collectShow: 2,
                collectionId: res.data.data.list[0].collectionId
              })
            }
          }
        }
      }
    })
  },

  // 变更
  changeSubject: function(step) {
    var that = this;
    let list = app.globalData.picList
    if (step < 0) {
      step = 0;
      wx.showToast({
        title: '下拉刷新',
        duration: 2000,
        icon: 'none'
      })
    }
    step = step || 0;
    if (list.length <= step) {
      wx.showToast({
        title: '已经到底了',
        duration: 2000,
        icon: 'none'
      })
      step = list.length - 1
      app.globalData.step = step
    }
    that.loadPic(step)
  },
  // 收藏
  toCollect(e) {
    let that = this
    let status = 3 - that.data.collectShow //收藏状态
    wx.request({
      url: app.globalData.serviceurl + '/collection/change',
      method: 'POST',
      data: {
        status: status,
        collectionId: that.data.collectionId
      },
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        console.log(res)
        if (res.data.code == 200) {
          //该表收藏状态改变成功
          wx.showToast({
            title: '操作成功',
            duration: 2500,
            icon: 'none'
          })
          that.setData({
            collectShow: status
          })
          that.onShow()
        } else {
          wx.showToast({
            title: '操作失败',
            duration: 2500,
            icon: 'none'
          })
        }
      }
    })
  },
  // 播放上一个抖音
  pre: function() {
    this.changeSubject(--app.globalData.step);
  },

  // 播放下一个抖音
  next: function() {
    this.changeSubject(++app.globalData.step);
  },

  touchstart: function(e) {
    start = e.changedTouches[0];
  },

  touchmove: function(e) {},

  touched: function(e) {
    this.getDirect(start, e.changedTouches[0]);
  },

  touchcancel: function(e) {
    this.getDirect(start, e.changedTouches[0]);
  },

  // 计算滑动方向
  getDirect(start, end) {
    var X = end.pageX - start.pageX,
      Y = end.pageY - start.pageY;
    if (Math.abs(X) > Math.abs(Y) && X > 0) {
      console.log("right");
    } else if (Math.abs(X) > Math.abs(Y) && X < 0) {
      console.log("left");
    } else if (Math.abs(Y) > Math.abs(X) && Y > 0) {
      console.log("bottom");
      this.pre();
    } else if (Math.abs(Y) > Math.abs(X) && Y < 0) {
      console.log("top");
      this.next()
    }
  },
  onLoad: function() {
    // 判断登录状态
    // 未登录需要进行登录
    // 登录跳过
    if (app.globalData.userId == "") {
      app.SignIn()
      this.setData({
        userId: app.globalData.userId,
        userInfo: app.globalData.userInfo
      })
    }
  },
  onShow: function() {
    app.loadPicList()
    this.loadPic(app.globalData.step)
  }
})