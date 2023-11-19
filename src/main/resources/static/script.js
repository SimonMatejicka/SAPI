let openedElement;
let topLeftDimen;
let hodina = {
    name:"", start:"", end:"",
};

$(".hodina").click(function(){
    if($("#hodina_popup").length >= 1){
        return
    }

    hodina.name = $(this).find('.text1').data('name');
    hodina.start = $(this).find('.start').data('start');
    hodina.end = $(this).find('.end').data('end');
    console.log()

    $("body").prepend("<div id='overlay'></div>")

    $(this).parent().append(
        "<div class='hodina popup' id='hodina_popup'>" +
            "<div class='text1'>" + hodina.name + "</div>" +
            "<div class='text2'>" +
                "<div class='time start'>" + hodina.start + "</div>" +
                "<div class='line'></div>" +
                "<div class='time end'>" + hodina.end + "</div>" +
            "</div>" +
            "<div class='button zrusit_zmenu' onclick='closeHodina(openedElement)'>Zrušiť zmenu</div>" +
            "<div class='button aplikovat_zmenu' onclick='aplikovanieHodiny(openedElement)'>Aplikovať zmenu</div>" +
        "</div>"
    );
    
    openedElement = $(this);
    openHodina($(this))
    //console.log($(this).css());
    //$(this).css({"position": "absolute"});
    //console.log()
});
/*
$(document).on("click", "#hodina_popup", function(){
    closeHodina(openedElement);
    //console.log($(this).css());
    //$(this).css({"position": "absolute"});
    //console.log()
});
*/
function aplikovanieHodiny(elApl){
    openedElement.find(".name").text("12. Hodina").data('name', "12. Hodina")
    openedElement.find(".start").text("23:40").data('start', "23:40")
    openedElement.find(".end").text("23:50").data('end', "23:50")
    closeHodina(elApl);
}
function openHodina(elFrom){
    const el = $("#hodina_popup");

    const cardHeight = $(".hodina").height() + 10;
    $(el).css({
        "margin-top": cardHeight * ($(elFrom).index()) + 10 + "px"
    });

    const finalDimensions = [$(window).width() * 0.8, $(window).height() * 0.4];

    topLeftDimen = {"top": $(el).css("top"), "left": $(el).css("left")}

    $(el).animate({
        "top": $(window).height() / 2 - finalDimensions[1] / 2,
        "left": $(window).width() / 2 - finalDimensions[0] / 2,
        "margin": 0,
        "width": finalDimensions[0],
        "height": finalDimensions[1],
        "background":"rgba(0,0,0,0)",
    }, 400);
}
function closeHodina(elTo){
    const el = $("#hodina_popup");

    const cardDimensions = [$(".hodina").width(), $(".hodina").height()];
    /*$(el).css({
        "margin-top": cardSize * ($(elFrom).index()) + 10 + "px"
    });*/

    const finalDimensions = [$(window).width() / 2, $(window).height() / 2];
    $("body").find("#overlay").remove();
    console.warn($(window).width())
    $(el).animate({
        "top": topLeftDimen["top"],
        "left": topLeftDimen["left"],
        "margin-top": (cardDimensions[1] + 10) * ($(elTo).index()) + 10 + "px",
        "margin-left": "10px",
        "margin-right": "10px",
        "width": cardDimensions[0],
        "height": cardDimensions[1],

    }, 400, function(){
        $(el).remove()
    });
}

function rozvrhChoice(){
    $("body").append(
        "<div id='rozvrhy'></div>"
    );
    $("body").prepend("<div id='overlay'></div>"
    )

    openedElement = $(this);
    openRozvrhy($(this))
}

$(document).on("click", "#rozvrhy", function(){
    closeRozvrhy(openedElement);
    //console.log($(this).css());
    //$(this).css({"position": "absolute"});
    //console.log()
});

function openRozvrhy(elFrom){
    const el = $("#rozvrhy");



    const finalDimensions = [$(window).width() * 0.9, $(window).height() * 0.8];

    topLeftDimen = {"top": $(el).css("top"), "left": $(el).css("left")}

    $(el).animate({
        "top": $(window).height() / 2 - finalDimensions[1] / 2,
        "left": $(window).width() / 2 - finalDimensions[0] / 2,
        "margin": 0,
        "width": finalDimensions[0],
        "height": finalDimensions[1],

    }, 400);

}

function closeRozvrhy(elTo){
    const el = $("#rozvrhy");

    const cardDimensions = [50, 50];
    /*$(el).css({
        "margin-top": cardSize * ($(elFrom).index()) + 10 + "px"
    });*/

    const finalDimensions = [$(window).width() / 2, $(window).height() / 2];
    $("body").find("#overlay").remove();
    console.warn($(window).width())
    $(el).animate({
        "top": topLeftDimen["top"],
        "left": topLeftDimen["left"],
        "margin-top": (cardDimensions[1] + 10) * ($(elTo).index()) + 10 + "px",
        "margin-left": "10px",
        "margin-right": "10px",
        "width": cardDimensions[0],
        "height": cardDimensions[1],

    }, 200, function(){
        $(el).remove()
    });
}

function aplikovanieRozvrhu(){

}
