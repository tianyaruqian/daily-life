

/*(function (factory) {
    //ajax 设置自定义header
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded",
        beforeSend: function (request) {
            request.setRequestHeader("x-token", "x-auth");
        },
        complete: function (xhr, data) {
            /!*
                获取相关Http Response header
                getResponseHeader(key)：获取指定头信息
                getAllResponseHeaders()：获取全部可默认可获取的头信息
            *!/
            var date = xhr.getResponseHeader('Date');// 服务器端时间

            //获取服务端自定义的header信息
            var stoken = xhr.getResponseHeader('servertoken');

            var list = xhr.getAllResponseHeaders();

            /!*
            date: Fri, 12 Jul 2019 12:41:00 GMT
            content-encoding: gzip
            server: Microsoft-IIS/10.0
            x-aspnet-version: 4.0.30319
            x-powered-by: ASP.NET
            vary: Accept-Encoding
            content-type: text/plain; charset=utf-8
            servertoken: test1
            cache-control: private
            content-length: 129
            *!/

        }

    });
}*/


