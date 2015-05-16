/**
 * 
 * @authors 吴儒林 (49106868@qq.com,http://wurulin.com)
 * @date    2015-05-07 15:04:44
 * @version $Id$
 */

$(document).ready(function() {
    $('.wp-inner').fullpage({
        page: '.page',
        start: 0,
        duration: 50,
        drag: false,
        loop: false,
        dir: 'h',
        change: function() {

        },
        beforeChange: function() {},
        afterChange: function(e) {
            //console.log(e)
            switchs(e.next);
        },
        orientationchange: function() {}
    });

    var nav_li = document.querySelectorAll("nav>li")
    var ele = document.querySelector("article");
    var animation_ps = {};
    animation_ps.mark = document.querySelector('.mark');
    animation_ps.place = document.querySelector('.place');
    animation_ps.coin = document.querySelector('.coin');
    animation_ps.box = document.querySelector('.box');
    animation_ps.music = document.querySelector('.music');



    function switchs(page) {

        switch (page) {
            case 1:

                nav_li[0].setAttribute('class', 'active');
                nav_li[1].removeAttribute('class');

                clearInterval(ani);
                animation_ps.music.removeAttribute('style');
                setTimeout(step2, 300)
                break;
            case 0:


                nav_li[1].setAttribute('class', 'active');
                nav_li[0].removeAttribute('class');

                setTimeout(step1, 200)
                break;
            default:
                break;
        }
    }


    //第一针
    function step1() {
        //Animation.dibiao(animation_ps.place);
        animation_ps.place.setAttribute('style', '-webkit-animation-play-state: initial;  -webkit-animation: roll-clockwise 0.5s linear 0s 1 normal forwards;');
        animation_ps.mark.style.bottom = '-25px';
        setTimeout(function() {
            animation_ps.coin.style.top = '-175px';
            animation_ps.coin.style.opacity = '1';
        }, 400)
    }

    function close_step1() {
        animation_ps.place.removeAttribute("style");
        animation_ps.mark.removeAttribute("style");
        animation_ps.coin.removeAttribute("style");
    }

    var ani = false;

    function step2() {
        close_step1();
        animation_ps.hand = document.querySelector('.hand');

        animation_ps.music.setAttribute('style', '-webkit-animation-play-state: initial; -webkit-animation: shan 0.8s linear 1s 1 normal forwards;');

        var a = 1,
            count = 0;

        ani = setInterval(function() {
            count++;

            a = a * -1;
            var b = 15 * a;
            if (count == 4) {
                b = 0;
                clearInterval(ani);
            };
            animation_ps.hand.style.transform = 'rotate(' + b + 'deg)';
            animation_ps.hand.style.webkitTransform = 'rotate(' + b + 'deg)';
        }, 200)
    }


})
