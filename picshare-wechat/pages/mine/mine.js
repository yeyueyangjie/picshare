// pages/person/person.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,
    navbar: [{
        name: "收藏",
        num: ""
      },
      {
        name: "作品",
        num: ""
      }
    ],
    userId: "",
    portrait: "",
    userName: "",
    address: "",
    piclist:[],
    collectlist:[],
    love: "0",
    fans: "0",
    atten: "0",
    id: "0",
    labelArr: [{
      name: ""
    }],

    photoArr: [{
      src: ""
    }, ],
    collectArr: [{
      src: "",
      num: "",
      url: "",
      id: "",
      object_id: ""
    }, ],
    worksArr: [{
      src: "",
      num: "",
      url: "",
      id: ""
    }, ],

    show: false,
    fansShow: false

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    let obj = app.globalData.userInfo
    this.setData({
      userId: app.globalData.userId,
      userName: obj.userName,
      portrait: obj.portrait,
      address: obj.address
    })

    console.log(this.data)
    // 获取收藏  作品
    wx.request({
      url: app.globalData.serviceurl + '/pic/collectlist',
      method: 'GET',
      data: {
        userId: that.data.userId
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function(res) {
        console.log(res)
        let collectlist = res.data.data.list
        let nav = that.data.navbar
        nav[0].num = res.data.data.count
        that.setData({
          navbar: nav,
          collectlist: collectlist
        })
      }
    })

    wx.request({
      url: app.globalData.serviceurl + '/pic/listbyauthor',
      method: 'GET',
      data: {
        userId: that.data.userId
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(res)
        let piclist = res.data.data.list
        let nav = that.data.navbar
        nav[1].num = res.data.data.count
        that.setData({
          navbar: nav,
          piclist: piclist
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.onLoad()
  },

  navbarTap(e) {
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
  },
  showSettings(e) {
    this.setData({
      show: true
    })
  },
  closeShow(e) {
    this.setData({
      show: false
    })
  },
  toAddLabel(e) {
    wx.navigateTo({
      url: '/pages/label/label',
    })
  },
  attenPerson(e) {
    var that = this;
    if (that.data.fansShow == false) {
      that.data.fansShow = true;
      wx.showToast({
        title: '您不能关注自己',
        icon: 'none',
        duration: 2000
      })
      return false;
    } else {
      that.data.fansShow = false;
    }
    that.setData({
      fansShow: that.data.fansShow
    })
  },
  cancleAtten(e) {
    var that = this;
    if (that.data.fansShow == true) {
      that.data.fansShow = false
      wx.showToast({
        title: '您已取消关注',
        icon: 'none',
        duration: 500
      })
    } else {
      that.data.fansShow = true
    }
    that.setData({
      fansShow: that.data.fansShow
    })
  },
  toFans(e) {
    wx.navigateTo({
      url: '/pages/fans/fans',
    })
  },
  toAtten(e) {
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/atten/atten?id=' + id,
    })
  },
  previewImg(e) {
    var id = e.currentTarget.dataset.id;
    //console.log(this.data.photoArr);
    var picList = [];
    for (var i = 0; i < this.data.photoArr[id].src.length; i++) {
      picList.push(this.data.photoArr[id].src[i]);
    }
    //console.log("这是图片"+picList)
    wx.previewImage({
      current: this.data.photoArr[id].src[0], // 当前显示图片的http链接
      urls: picList // 需要预览的图片http链接列表
    })
  },
  toDetail(e) {
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/video/video?id=' + id,
    })
  },
  toFavorite(e) {
    var id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/video/video?id=' + id,
    })
  }
})