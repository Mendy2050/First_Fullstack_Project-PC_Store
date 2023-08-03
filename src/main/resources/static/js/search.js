$(function () {
    /*Product list, add shadow when the mouse moves in, remove shadow when moving out*/
    $(".goods-panel").hover(function () {
        $(this).css("box-shadow", "0px 0px 8px #888888");
    }, function () {
        $(this).css("box-shadow", "");
    })
//When joining Favorites, ♥'s solid hollow switch
    $(".add-fav").toggle(function () {
        $(this).html("<span class='fa fa-heart'></span>Cancel Favorites");
    }, function () {
        $(this).html("<span class='fa fa-heart-o'></span>Join Favorites");
    })
})