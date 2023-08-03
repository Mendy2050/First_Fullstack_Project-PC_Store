//Modify this variable to the address of the actual controller, such as ../showGoods.do
var reqpath = "search.html"
/*json object obtained by ajax*/
var typelist = [{
    "id": "1",
    "parentId": "0",
    "name": "Book Audio"
}, {
    "id": "2",
    "parentId": "0",
    "name": "Household Appliances"
}, {
    "id": "3",
    "parentId": "0",
    "name": "Computer Office"
}, {
    "id": "4",
    "parentId": "0",
    "name": "Personal Care Makeup"
}, {
    "id": "5",
    "parentId": "0",
    "name": "Clock"
}, {
    "id": "6",
    "parentId": "0",
    "name": "Mother and Baby"
}, {
    "id": "7",
    "parentId": "0",
    "name": "Food and Beverage"
}, {
    "id": "8",
    "parentId": "0",
    "name": "Car Supplies"
}, {
    "id": "9",
    "parentId": "0",
    "name": "Toy Musical Instrument"
}, {
    "id": "10",
    "parentId": "0",
    "name": "Mobile Phone"
}, {
    "id": "11",
    "parentId": "0",
    "name": "Digital"
}, {
    "id": "12",
    "parentId": "0",
    "name": "Home Improvement"
}, {
    "id": "13",
    "parentId": "0",
    "name": "Kitchenware"
}, {
    "id": "14",
    "parentId": "0",
    "name": "Apparel Underwear"
}, {
    "id": "15",
    "parentId": "0",
    "name": "Shoes"
}, {
    "id": "16",
    "parentId": "0",
    "name": "Gift Bag"
}, {
    "id": "17",
    "parentId": "0",
    "name": "Jewelry"
}, {
    "id": "18",
    "parentId": "0",
    "name": "Sports Health"
}, {
    "id": "19",
    "parentId": "0",
    "name": "Recharge Ticket"
}, {
    "id": "20",
    "parentId": "3",
    "name": "Computer"
}, {
    "id": "21",
    "parentId": "3",
    "name": "Computer Accessories"
}, {
    "id": "22",
    "parentId": "3",
    "name": "Peripheral Products"
}, {
    "id": "23",
    "parentId": "3",
    "name": "Network Product"
}, {
    "id": "24",
    "parentId": "3",
    "name": "Office Equipment"
}, {
    "id": "25",
    "parentId": "3",
    "name": "Stationery Supplies"
}, {
    "id": "26",
    "parentId": "3",
    "name": "Service Product"
}, {
    "id": "27",
    "parentId": "20",
    "name": "Notebook"
}, {
    "id": "28",
    "parentId": "20",
    "name": "Ultrabook"
}, {
    "id": "29",
    "parentId": "20",
    "name": "Gamebook"
}, {
    "id": "30",
    "parentId": "20",
    "name": "Tablet"
}, {
    "id": "31",
    "parentId": "20",
    "name": "Tablet Accessories"
}, {
    "id": "32",
    "parentId": "20",
    "name": "Desktop"
}, {
    "id": "33",
    "parentId": "20",
    "name": "Server Workstation"
}, {
    "id": "34",
    "parentId": "20",
    "name": "Notebook Accessories"
},]

//The method of loading json data to the first level classification
function initMenu() {
    for (var i = 0; i < typelist.length; i++) {

        if (typelist[i].parentId == "0") {

            $(".index-menu").append($("<li data='" + typelist[i].id + "'>" + typelist[i].name + "</li>"))
        }
    }
}

window.addEventListener("load", function () {
    initMenu();
//According to the height of the carousel image, the height of the navigation
//Get the height of the carousel image
    var lunh = $("#myCarousel").height();
    var lih = (lunh - 10) / 19;
//determine navigation height
    $(".index-menu li").css("height", lih + "px")
//determine button position
    var btnt = Math.floor($("#myCarousel").height() / 2 - 30);
    $(".left").css("margin-top", btnt + "px");
    $(".right").css("margin-top", btnt + "px");
    /* The first-level menu of the left category controls the display and hiding of the second-level menu */
    $(".index-menu").hover(function () {
        $("#showIndex").show();
    }, function () {
        $("#showIndex").hide();
    })
    /*Left classification level 2 menu controls display and hiding of level 3 menu*/
    $(".second-menu").hover(function () {
        $("#showSecond").show();
    }, function () {
        $("#showSecond").hide();
    })
    /*The secondary menu controls the display and hiding by itself*/
    $("#showIndex").hover(function () {
        $("#showIndex").show();
    }, function () {
        $("#showIndex").hide();
    })
    /*The third-level menu controls the display and hiding by itself*/
    $("#showSecond").hover(function () {
        $("#showIndex").show();
        $("#showSecond").show();
    }, function () {
        $("#showIndex").hide();
        $("#showSecond").hide();
    })
    /* Level 1 classification item li mouse moves in and out*/
    var offTop = -100;
    var offLeft = 0;
    $(".index-menu li").hover(function () {
        $(".second-menu").empty();
        /*load json data*/
        for (var i = 0; i < typelist.length; i++) {
            if ($(this).attr("data") == typelist[i].parentId) {
                $(".second-menu").append($("<li class='second-menu-li' data='" + typelist[i].id +
                    "' >" + typelist[i].name + "</li>"))
            }
        }
        offLeft = $(this).width() + $(this).offset().left;
        offTop = $(this).offset().top;
        $("#showIndex").css("top", offTop - 2 + "px")
        $("#showIndex").css("left", offLeft - 1 + "px")
        $(this).css("background-color", "#f5f5f5");
        $(this).css("color", "#4288c3");
    }, function () {
        $(this).css("background-color", "");
        $(this).css("color", "");
    })
    /*Secondary classification item li mouse moves in and out*/
    $(".second-menu-li").live("mouseover", function () {
        $(".third-menu").empty();
        /*Download Data*/
        for (var i = 0; i < typelist.length; i++) {
            if ($(this).attr("data") == typelist[i].parentId) {
            }
//alert($(document).scrollTop() + ":"+$(this).offset().top)
            var ot = $(document).scrollTop() == $(this).offset().top ? offTop : $(this).offset().top;
            var ol = $(this).width() + $(this).offset().left;
            $("#showSecond").css("top", ot - 2 + "px");
            $("#showSecond").css("left", ol + "px")
            $(this).css("background-color", "#4288c3");
            $(this).css("color", "#f5f5f5");
        }
        $(".second-menu-li").live("mouseout", function () {
            $(this).css("background-color", "");
            $(this).css("color", "");
        })
        /*Three-level classification item li mouse moves in and out*/
        $(".third-menu-li").live("mouseover", function () {
            $(this).css("background-color", "#dddddd");
            $(this).css("color", "#000000");
        })
        $(".third-menu-li").live("mouseout", function () {
            $(this).css("background-color", "");
            $(this).css("color", "");
        })
    })
})