/**
 * Created by baidu on 16/6/8.
 */


// var stream = require('@weex-module/stream');//说是0.15已经支持,但是我没生效

var stream
__weex_define__('@weex-temp/api', function (__weex_require__) {
    stream = __weex_require__('@weex-module/stream')
});

var apiURL = {
    baseurl: 'http://v3.wufazhuce.com:8000/api',
    homePage: '/hp/bymonth/',
    readingCarousel: '/reading/carousel',
    readingIndex: '/reading/index/',
    essay: '/essay/',
    serialcontent: '/serialcontent/',
    question: '/question/',
    carouselList: '/reading/carousel/',
    movieList: '/movie/list/',
    movieDetail: '/movie/detail/'

};
function getData(url, callback) {
    stream.sendHttp({
        method: 'GET',
        url: url
    }, function (ret) {
        var retdata = JSON.parse(ret);
        callback(retdata);
    });
}
exports.getHome = function (dateStr, callback) {
    getData(apiURL.baseurl + apiURL.homePage + dateStr, callback);
};
exports.getReadingCarousel = function (callback) {
    getData(apiURL.baseurl + apiURL.readingCarousel, callback);
};
exports.getReadingIndex = function (index, callback) {
    getData(apiURL.baseurl + apiURL.readingIndex + index, callback);
};
exports.getEssay = function (id, callback) {
    getData(apiURL.baseurl + apiURL.essay + id, callback);
};
exports.getSerialContent = function (id, callback) {
    getData(apiURL.baseurl + apiURL.serialcontent + id, callback);
};
exports.getQuestionDetail = function (id, callback) {
    getData(apiURL.baseurl + apiURL.question + id, callback);

};
exports.getCarouseList = function (id, callback) {
    getData(apiURL.baseurl + apiURL.carouselList + id, callback);
};
exports.getMovieList = function (id, callback) {
    getData(apiURL.baseurl + apiURL.movieList + id, callback);
};
exports.getMovieDetail = function (id, callback) {
    getData(apiURL.baseurl + apiURL.movieDetail + id, callback);

};

exports.getBaseUrl = function (bundleUrl, isnav) {
    bundleUrl = new String(bundleUrl);
    var nativeBase;
    var isAndroidAssets = bundleUrl.indexOf('file://assets/') >= 0;

    var isiOSAssets = bundleUrl.indexOf('file:///') >= 0 && bundleUrl.indexOf('WeexDemo.app') > 0;
    if (isAndroidAssets) {
        nativeBase = 'file://assets/dist/';
    }
    else if (isiOSAssets) {
        nativeBase = bundleUrl.substring(0, bundleUrl.lastIndexOf('/') + 1);
    }
    else {
        var host = 'localhost:12580';
        var matches = /\/\/([^\/]+?)\//.exec(bundleUrl);
        if (matches && matches.length >= 2) {
            host = matches[1];
        }

        //此处需注意一下,tabbar 用的直接是jsbundle 的路径,但是navigator是直接跳转到新页面上的.
        if (typeof window === 'object') {
            nativeBase = isnav ? 'http://' + host + '/index.html?page=./dist/' : '/dist/';
        } else {
            nativeBase = 'http://' + host + '/dist/';
        }
    }

    return nativeBase;
};