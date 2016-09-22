

## WEEXONE

## Update
2016-09-22 更新了WeexOne 兼容了新版本的Weex，还需要解决UI错乱的问题

## 介绍

端午的时候收到了Weex团队发的一个Weex Workshop的邮件，然后就利用这两天使用Weex做了一款[one 一个]的客户端。源代码在：[https://github.com/dodola/WeexOne][1]


![enter description here][2]


![enter description here][3]



![enter description here][4]


![enter description here][5]


![enter description here][6]

![enter description here][7]

## Build & Run
1. Prerequisites
	* Download Weex Project Source
	* Install Node.js 4.0+
	* Under project root
		* `npm install`, install project
2. Run playground, In Android Studio
	* Open `android/playground `
	* In `app/java/com.alibaba.weex/WXMainActivity`, modify `CURRENT_IP` to your local IP
	* Copy WeexOne source folder to `examples` folder
	* Add WeexOne to `index.we`
	```javascript
        {name: 'WeexOne/index', title: 'WeexOne'}

	```
	* Run app in Android Studio  (Run button)
	* In Weex project folder run `./start`



## 关于Weex

我关注ReactNative和Hybrid相关的技术也有一段时间了，但是由于项目中没有这方面的需求所以也没有真正的应用到开发中。知道Weex是从淘宝的一篇技术文档里知晓这个技术的，从那时候起就一直期待weex开源。


Weex从上手来说还是相当容易的，我看了一会文档就可以开始做页面了，语法套用的是原生的css、html、javascript的语法，并没有像ReactNative那样开发一个JSX语言还需要适应这种语法，相对来说Weex要比ReactNative上手要简单很多的。


从生成的包的大小来看我的这个应用没混淆状态下apk是3.9MB，相对于ReactNative的7.8MB的大小还是要小很多的，从界面流畅度来看Weex可能要比ReactNative要流畅和灵敏一些。


上面说了一些优点，下面说一下可能需要改进的方面。


相对于ReactNative来说其明显的缺点就是没有一套完整的构建系统，可能和RN的目的不同，ReactNative似乎一直想让用户完全使用RN开发一个完整的应用，而weex给人的感觉就是想让人嵌入到现有应用中，这样。个人感觉。。


另一方面就是调试机制和IDE的支持情况。Weex现在没有一个完整的可调试的方案，一般都采用日志输出的方式，IDE的支持方面暂时也只有语法支持，不过由于weex采用的都是原生的html、css、js语法，所以ide采用之前熟悉的工具就可以。这方面我觉得不是什么比较严重的缺陷，随着项目的发展这方面肯定也会慢慢发展起来。


一个比较明显的问题是适配问题，weex将整个app的宽度定死在750px，然后其他都是根据scale进行计算的，这样就会导致一个问题就是无法通过设计图的标注来准确的计算应用运行起来具体尺寸，字体的尺寸也是难以把握的，我暂时是一个像素一个像素的调整，还没有遇到比较好的方法，如果大家有好的方法可以分享出来。


有个硬伤问题....weex不支持本地图片，暂时必须使用服务器或者网络上的图片才可以，这样就会造成的问题就是网络不好的情况下一些图标不是立即可见的。这个问题比较严重。

开发过程中没有遇到大的难题，基本上看文档和例子代码就好了，像开发weexone这种小项目一般两天就做完了,从开发效率上看快很多。


  [1]: https://github.com/dodola/WeexOne
  [2]: ./images/1.png "1.png"
  [3]: ./images/2.png "2.png"
  [4]: ./images/3.png "3.png"
  [5]: ./images/4.png "4.png"
  [6]: ./images/5.png "5.png"
  [7]: ./images/6.png "6.png"
