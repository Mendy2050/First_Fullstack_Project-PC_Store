$(function () {
    /*The small picture of the product adds the mouse to move in and add a frame, move out to remove the frame*/
    $(".img-small").hover(function () {
            $(this).css("border", "1px solid #4288c3");
        },
        function () {
            $(this).css("border", "");
        })
// Change the big picture when clicked
    $(".img-small").click(function () {
//Get the clicked small picture data
        var n = $(this).attr("data");
//All large images are hidden
        $(".img-big").hide();
//Display the large image corresponding to the clicked small image
        $(".img-big[data='" + n + "']").show();
    })
//Increase the shopping quantity by 1
    $("#numUp").click(function () {
        var n = parseInt($("#num").val());
        $("#num").val(n + 1);
    })
//Shopping Quantity - 1
    $("#numDown").click(function () {
        var n = parseInt($("#num").val());
        if (n == 1) {
            return;
        }
        $("#num").val(n - 1);
    })
//Click the Cart to jump to the page
    $(".go-cart").click(function () {
        location.href = "cart.html";
    });
    $(".img-big:eq(0)").show();
})