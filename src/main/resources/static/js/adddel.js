$(function () {
    //Prompt when address management clicks delete
    $(".add-del").click(function () {
        if (confirm("Are you sure you want to delete this address?")) {
            location.href = "address.html";
        }
    })
    //The effect of clicking "Set as default address"
    $(".add-def").click(function () {
        $(".add-def").show();
        $(this).hide();
    })
    $(".add-def:eq(0)").hide();
})