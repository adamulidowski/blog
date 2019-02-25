$(document).ready(function () {
    document.getElementById("buttonImg").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[OBRAZ]Url obrazu[/OBRAZ]';
        text.focus();
    });

    document.getElementById("buttonImgText").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[PODPIS]Podpis obrazu/kodu[/PODPIS]';
        text.focus();
    });

    document.getElementById("buttonCode").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[CODE]Miejsce na kod[/CODE]';
        text.focus();
    });

    document.getElementById("buttonTab").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[T]';
        text.focus();
    });

    document.getElementById("buttonBold").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[B]Tekst[/B]';
        text.focus();
    });

    document.getElementById("buttonIt").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[I]Tekst[/I]';
        text.focus();
    });
    document.getElementById("buttonCt").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '[C]Tekst[/C]';
        text.focus();
    });
    document.getElementById("buttonURL").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '<a href="URL">Tekst</a>';
        text.focus();
    });
    document.getElementById("buttonOl").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '<ul> <li>Punkt pierwszy</li> <li>Punkt drugi</li> </ul>';
        text.focus();
    });
    document.getElementById("buttonOl").addEventListener('click', function () {
        var text = document.getElementById('text');
        text.value += '<ol> <li>Punkt pierwszy</li> <li>Punkt drugi</li> </ol>';
        text.focus();
    });
});
