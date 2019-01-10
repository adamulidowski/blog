$(document).ready(function () {
    $("#coding").hover(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "Programowanie";
    });

    $("#football").hover(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "Sport";
    });

    $("#moviess").hover(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "Popkultura";
    });

    $("#list").hover(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "Lista wpisów";
    });

    $("#about").hover(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "O mnie";
    });

    $("#icons").mouseleave(function () {
        var element = document.getElementById("tittle");
        element.innerHTML = "Junior Developer | Blog";
    });
});