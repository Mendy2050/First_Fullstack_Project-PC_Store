/* Increase by the number of plus signs */
function addNum(rid) {
    var n = parseInt($("#goodsCount" + rid).val());
    $("#goodsCount" + rid).val(n + 1);
    calcRow(rid);
}

/* Subtract according to the number of minus signs */
function reduceNum(rid) {
    var n = parseInt($("#goodsCount" + rid).val());
    if (n == 0)
        return;
    $("#goodsCount" + rid).val(n - 1);
    calcRow(rid);
}

/*Select all not select*/
function checkall(ckbtn) {
    $(".ckitem").prop("checked", $(ckbtn).prop("checked"));
    //calcTotal();
}

//delete button
function delCartItem(btn) {

    $(btn).parents("tr").remove();
    //calcTotal();
}

//Batch delete button
function selDelCart() {
// loop through all buttons
    for (var i = $(".ckitem").length - 1; i >= 0; i--) {
        //if selected
        if ($(".ckitem")[i].checked) {
            //delete
            $($(".ckitem")[i]).parents("tr").remove();
        }
    }
    //calcTotal();
}

$(function () {
    // Single selection also needs to calculate the price
    $(".ckitem").click(function () {
        //calcTotal();
    })
    // Calculate the price at the beginning
    //calcTotal();
})

//The method of calculating the subtotal price of a single line
function calcRow(rid) {
    //Get the unit price
    var vprice = parseFloat($("#goodsPrice" + rid).html());
    //take quantity
    var vnum = parseFloat($("#goodsCount" + rid).val());
    //subtotal amount
    var vtotal = vprice * vnum;
    //assignment
    $("#goodsCast" + rid).html("€" + vtotal);
}

//method to calculate the total price
/*
function calcTotal() {
//number of items selected
var vselectCount = 0;
//The total price of the selected item
var vselectTotal = 0;

//Loop through all tr
for (var i = 0; i < $(".cart-body tr").length; i++) {
//Calculate the price subtotal for each item to start
// fetch 1 row
var $tr = $($(".cart-body tr")[i]);
//Get the unit price
var vprice = parseFloat($tr. children(":eq(3)"). children("span"). html());
//take quantity
var vnum = parseFloat($tr. children(":eq(4)"). children(".num-text"). val());
//subtotal amount
var vtotal = vprice * vnum;
//assignment
$tr.children(":eq(5)").children("span").html("€" + vtotal);
//Calculate the price subtotal end of each item

// check if selected
if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
//count
vselectCount++;
//total price
vselectTotal += vtotal;
}
//Assign the selected quantity and price
$("#selectTotal").html(vselectTotal);
$("#selectCount").html(vselectCount);
}
}*/