package com.example.sheng.mplayerv1;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                    /  _||||| -:- |||||-  \
 * //                    |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . __
 * //               ."" '<  `.___\_<|>_/___.'  >'"".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                      Buddha Bless, No Bug !
 * /**
 * Created by st on 2018/7/3
 */
public interface Api {

    String BASE_URL="http://www.go1977.com/";
    //电影
    String ACTION_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-5.html";//动作片
    String COMEDY_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-6.html";//喜剧片
    String LOVE_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-7.html";//爱情片
    String SCIENCE_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-8.html";//科幻片
    String HORROR_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-9.html";//喜剧片
    String FEATURE_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-10.html";//恐怖片
    String WAR_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-11.html";//战争片
    String ETHIC_MOVIE_URL="http://www.go1977.com/?m=vod-type-id-16.html";//伦理片
    //电视剧
    String CHINA_TV_URL="http://www.go1977.com/?m=vod-type-id-12.html";//国产剧
    String GANGTAI_TV_URL="http://www.go1977.com/?m=vod-type-id-13.html";//港台剧
    String EURAMERICAN_TY_URL="http://www.go1977.com/?m=vod-type-id-14.html";//欧美剧
    String RIHAN_TY_URL="http://www.go1977.com/?m=vod-type-id-15.html";//日韩剧
    //其他
    String VARIETY_URL="http://www.go1977.com/?m=vod-type-id-3.html";//综艺
    String ANIMATION_URL="http://www.go1977.com/?m=vod-type-id-4.html";//动漫
    String DOCUMENTARY_URL="http://www.go1977.com/?m=vod-type-id-17.html";//记录片

}
