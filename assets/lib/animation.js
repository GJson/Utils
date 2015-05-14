var Animation = (function() {

    var ps = {};

    ps.opacity_0 = {
        'opacity': '0'
    };
    ps.opacity_1 = {
        'opacity': '0',
        'left ': '-100px'
    };


    //定义方法
    $.keyframe.define([{
        name: 'roll-clockwise',
        '0%': {

        },
        '70%': {
            "bottom": "45px",
            "opacity": "1"
        },
        '100%': {
            "bottom": "65px",
            "opacity": "1"
        }
    }, {
        name: 'shan',
        '33%': {
            "opacity": "1"
        },
        '66%': {
            "opacity": "0"
        },
        '100%': {
            "opacity": "1"
        }
    }]);

    function dibiao(param) {

        $(param).playKeyframe({
            name: 'roll-clockwise',
            duration: '0.5s',
            timingFunction: 'linear',
            delay: '0s',
            iterationCount: '1',
            direction: 'normal',
            fillMode: 'forwards',
            complete: function() {}
        });
    }

    function shan(param) {
        $(param).playKeyframe({
            name: 'shan',
            duration: '0.8s',
            timingFunction: 'linear',
            delay: '1s',
            iterationCount: '1',
            direction: 'normal',
            fillMode: 'forwards',
            complete: function() {}
        });
    }

    return {
        version: "1.0",
        dibiao: dibiao,
        shan:shan
    };

})();
