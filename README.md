

## WEEXONE

**注：**
我在 [Weexpack][8] 分支下实现了用WeexPack进行打包的配置

## Update
* 2016-10-31 修复h5里tab和navigator路径错误问题，增加了weexone脚本
* 2016-10-30 修复了h5下相对路径取值错误问题
* 2016-10-26 更新了Android端离线方式，解决了相对路径的问题
* 2016-10-10 更新了WeexOne的编译运行方式
* 2016-09-22 更新了WeexOne 兼容了新版本的Weex，还需要解决UI错乱的问题

## Install
```bash
npm install
```
## Build & Run
1. `npm run buld`: build all we file into dist/* folder
2. `npm run dev`: watch file changes of src and automatically build into dist/
3. `npm run serve`: perview in html5 render through :`http://localhost:12580`


**OR**

1. `weexone`:build all we file into dist/* folder,and start default server with 12580 port
2. `weexone android`:     
  build we file into dist/* and copy all dist into android assets/dist folder     
  then build android    
  install apk to device    
  run weex playground    
 
## Run with weex playground app
1. open weex playground app
2. open qrcode scan ,scan `http://your weex server ip/dist/main.js`

## 介绍

端午的时候收到了Weex团队发的一个Weex Workshop的邮件，然后就利用这两天使用Weex做了一款[one 一个]的客户端。源代码在：[https://github.com/dodola/WeexOne][1]


![enter description here][2]


![enter description here][3]



![enter description here][4]


![enter description here][5]


![enter description here][6]

![enter description here][7]






  [1]: https://github.com/dodola/WeexOne
  [2]: ./images/1.png "1.png"
  [3]: ./images/2.png "2.png"
  [4]: ./images/3.png "3.png"
  [5]: ./images/4.png "4.png"
  [6]: ./images/5.png "5.png"
  [7]: ./images/6.png "6.png"
  [8]: https://github.com/dodola/WeexOne/tree/weexpack
