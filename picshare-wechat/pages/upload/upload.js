const app = getApp();
const qiniuUploader = require("../../utils/qiniuUploader")
// 初始化七牛相关参数
function initQiniu() {
  var options = {
    region: 'ECN', // 华东区
    uptokenURL: app.globalData.serviceurl + '/pic/uploadtoken',
    domain: 'http://picshare-wxchat.henghengya.cn',
    shouldUseQiniuFileName: false
  };
  qiniuUploader.init(options)
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tempFile: "",
    title: "",
    summary: "",
    imageurl: "",
    location:{}

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this
    initQiniu()
    // 获取当前位置信息
    let test = (obj) => {
      let flag = Object.keys(obj)
      return (flag.length === 0)
    }

    if (test(app.globalData.location)) {
      console.log('进行当前位置获取')
      // 获取当前位置信息
      wx.chooseLocation({
        success: function (res) {
          console.log(res)
          app.globalData.location = res
        }
      })
    } 
    console.log(app.globalData)
  },
  // 七牛图片上传方法
  upload: function(filePath) {
    let that = this
    qiniuUploader.upload(filePath, (res) => {
        console.log(res)
        that.setData({
          imageurl: res.fileUrl
        })
      }, (error) => {
        console.error('error: ' + JSON.stringify(error));
      });
  },
  /**
   * 表单提交
   */
  formSubmitPhoto: function(e) {
    var that = this;
    var info = e.detail.value;
    console.log(info)
    //console.log(that.data.imageurl)
    // console.log(info);
    if (info.title.length == 0 || info.summary == 0 || that.data.imageurl == "") {
      wx.showToast({
        title: "内容不能为空",
        duration: 1000,
        icon: 'none'
      });
      return false;
    }
    let image = that.data.imageurl
    let address = app.globalData.location.address
    let userid = app.globalData.userId
    
    wx.request({
      url: app.globalData.serviceurl + '/pic/add',
      data: {
        target: image,
        title: info.title,
        summary: info.summary,
        author: userid,
        address: address
      },
      method:'POST',
      header: {
        'content-type': 'application/json'
      },
      success(res){
        console.log(res) 
        if (res.data.code == 200) {
          // 发布成功
          wx.showToast({
            title: '发布成功',
            icon: 'none',
            duration: 5000
          })
        }
        else{
          wx.showToast({
            title: '发布异常',
            icon: 'none',
            duration: 5000
          })
        }
      },
      fail(res){
        wx.showToast({
          title: '发布异常',
          icon: 'none',
          duration: 5000
        })
      },
      complete(res){
        wx.reLaunch({
          url: '/pages/upload/upload',
        })
      }
    })
  },
  // 图片拍摄
  chooseImg(e) {
    var that = this;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        console.log(res);
        that.setData({
          tempFile: res.tempFilePaths[0],
        })
        that.upload(res.tempFilePaths[0])
      }
    })
  }
})