/************************************************************************/
/************************************************************************/
/*********      COPYRIGHT (C) Ruwix 2019   			 ********************/
/*********      WWW.HTML-ONLINE.COM                  ********************/
/************************************************************************/
/************************************************************************/
function GetCookie(e) {
    for (var t = e + "=", x = t["length"], o = document["cookie"]["length"], i = 0; i < o;) {
        var _ = i + x;
        if (document["cookie"]["substring"](i, _) == t) return "here";
        if (0 == (i = document["cookie"]["indexOf"]("", i) + 1)) break
    }
    return null
}

function testFirstCookie() {
    if (null == GetCookie("FirstTimeVisitHere")) {
        var e = new Date;
        e = new Date(e["getTime"]() + 7776e6), document["cookie"] = "FirstTimeVisitHere=here; expires=" + e, tinyMCE["activeEditor"]["setContent"](sourceEditorFricc["getValue"]())
    }
}

function startFirstTour() {
    0 == demoElinditva && (beforeDemoText = sourceEditorFricc["getValue"](), sourceEditorFricc["setValue"](demoContent), inputChanged(), demoElinditva = 1, updateRight()), introJs()["start"]()["oncomplete"](function() {
        demoElinditva = 0, sourceEditorFricc["setValue"](beforeDemoText), inputChanged()
    }), pentekenElkur()
}

function createCookie(e, t, x) {
    var o;
    if (x) {
        var i = new Date;
        i["setTime"](i["getTime"]() + 24 * x * 60 * 60 * 1e3), o = "; expires=" + i["toGMTString"]()
    } else o = "";
    document["cookie"] = encodeURIComponent(e) + "=" + encodeURIComponent(t) + o + "; path=/"
}

function readCookie(e) {
    for (var t = encodeURIComponent(e) + "=", x = document["cookie"]["split"](";"), o = 0; o < x["length"]; o++) {
        for (var i = x[o]; i["charAt"](0) === "";) i = i["substring"](1, i["length"]);
        if (0 === i["indexOf"](t)) return decodeURIComponent(i["substring"](t["length"], i["length"]))
    }
    return null
}

function eraseCookie(e) {
    createCookie(e, "", -1)
}

function popupBox(e) {
    document["getElementById"]("popupUzenet")["innerHTML"] = e, document["getElementById"]("popupUzenetBox")["style"]["display"] = "block"
}

function undoPressed() {
    text = sourceEditorFricc["getValue"](), sourceEditorFricc["setValue"](undotext), undotext = text, inputChanged()
}

function pentekenElkur() {
    1 == friday && (sourceEditorFricc["setValue"](""), tinyMCE["activeEditor"]["setContent"](""))
}

function checkIfItsFriday() {
    (new Date)["getDay"]();
    setTimeout(function() {}, 1e3)
}

function updateRight() {
    //var e = $(".szekcio3 .CodeMirror-scroll")["scrollTop"]();
    //console["log"]("SRCTOP -", e), setTimeout(function() {
    //    0 == friday && (sourceEditorFricc["setValue"](tinymce["get"]("elm1")["getContent"]()), sourceScrolled = 1), $(".szekcio3 .CodeMirror-scroll")["scrollTop"](e)
    //}, 10)
}

function updateLeft() {
    0 == friday && (0 == wysiwygActive && tinyMCE["activeEditor"]["setContent"](sourceEditorFricc["getValue"]()), 1 == scrollEditorsTogether && 0 != sourceEditorObject && setVisualScroll(sourceEditorObject))
}

function logol(e) {
    document["getElementById"]("logContainer")["innerHTML"] = document["getElementById"]("logContainer")["innerHTML"] + e + ""
}

function inputChanged() {
    //pentekenElkur(), document["getElementById"]("inputLength")["innerHTML"] = "Source:" + sourceEditorFricc["getValue"]()["length"], sourceEditorFricc["getValue"]()["length"] > 8999 && 0 == freeCountry ? ($("#warningAboveSource")["fadeIn"](200), $("#warningAboveSourceText")["html"]("<h2>Free Demo Limit Reached</h2><p>Please purchase a membership to remove the character limit and access&nbsp;the&nbsp;premium&nbsp;features.</p><a target=\"_blank\" rel=\"nofollow\" href=\"https://htmlg.com/license/?ref=characterlimit\">Subscribe</a>")) : $("#warningAboveSource")["fadeOut"](200), updateLeft()
}

function deletePressed() {
    undotext = sourceEditorFricc["getValue"](), sourceEditorFricc["setValue"](""), inputChanged()
}

function actualizeReplaceket() {
    for (var e = 1; e <= 12; e++) 1 == replaceaktiv[e] ? document["getElementById"]("replace" + e)["style"]["display"] = "block" : document["getElementById"]("replace" + e)["style"]["display"] = "none"
}

function addRepField() {
    for (var e = 0, t = 1; 0 == e && t <= 12;) 0 == replaceaktiv[t] && (replaceaktiv[t] = 1, e = 1), t++;
    0 == e && popupBox("You reached the limit"), actualizeReplaceket()
}

function deleteRepField(e) {
    1 == e && (replacetext1["value"] = "", replacewith1["value"] = ""), 2 == e && (replacetext2["value"] = "", replacewith2["value"] = ""), 3 == e && (replacetext3["value"] = "", replacewith3["value"] = ""), 4 == e && (replacetext4["value"] = "", replacewith4["value"] = ""), 5 == e && (replacetext5["value"] = "", replacewith5["value"] = ""), 6 == e && (replacetext6["value"] = "", replacewith6["value"] = ""), 7 == e && (replacetext7["value"] = "", replacewith7["value"] = ""), 8 == e && (replacetext8["value"] = "", replacewith8["value"] = ""), 9 == e && (replacetext9["value"] = "", replacewith9["value"] = ""), 10 == e && (replacetext10["value"] = "", replacewith10["value"] = ""), 11 == e && (replacetext11["value"] = "", replacewith11["value"] = ""), 12 == e && (replacetext12["value"] = "", replacewith12["value"] = ""), replaceaktiv[e] = 0, actualizeReplaceket()
}

function bodyOnload() {
    opt[16] = 1, editortUjraInicializal(), setTimeout(function() {
        initoptions()
    }, 2e3)
}

function initoptions() {
    //freeCountry = 0;
    //var e = (new Date)["getTimezoneOffset"]() / 60;
    //(3 == e || e < -1 && e > -9) && (freeCountry = 1);
    //var t = window["navigator"]["language"]["substring"](0, 2);
    //t != "hi" && t != "hu" && t != "pt" && t != "tr" && t != "zh" || (freeCountry = 1);
    //for (x = 0; x <= 54; x++) opt[x] = 0;
    //for (opt[1] = 1, opt[2] = 1, opt[3] = 1, opt[4] = 1, opt[16] = 1, opt[6] = 1, opt[10] = 1, opt[13] = 1, ij = 0; ij <= 20; ij++) document["cookie"]["indexOf"]("elmentettszettingek" + ij) >= 0 && (opt[ij] = readCookie("elmentettszettingek" + ij));
    //for (x = 0; x < 17; x++) 0 == opt[x] ? document["getElementById"]("optionButton" + x)["style"]["backgroundPosition"] = "10px 0px" : document["getElementById"]("optionButton" + x)["style"]["backgroundPosition"] = "10px -66px";
    //for (var x = 2; x <= 17; x++) replaceaktiv[x] = 0;
    //replaceaktiv[1] = 1, sourceEditorFricc["setSize"]("100%", 740), inputChanged(), testFirstCookie(), 0 == opt[16] && (tinyMCE["activeEditor"]["remove"](), editortUjraInicializal(), updateLeft(), updateRight())
}

function editortUjraInicializal() {
    1 == opt[16] ? tinymce["init"]({
        selector: "textarea#elm1",
        theme: "modern",
        convert_urls: !1,
        entity_encoding: "named",
        plugins: ["advlist autolink link image lists charmap print preview hr anchor", "searchreplace visualblocks visualchars code fullscreen insertdatetime media nonbreaking", "table directionality emoticons paste textcolor"],
        add_unload_trigger: !1,
        toolbar: "undo redo | code | removeformat | styleselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons table",
        image_advtab: !0,
        style_formats: [{
            title: "Heading 1",
            format: "h1"
        }, {
            title: "Heading 2",
            format: "h2"
        }, {
            title: "Heading 3",
            format: "h3"
        }, {
            title: "Heading 4",
            format: "h4"
        }, {
            title: "Paragraph",
            format: "p"
        }, {
            title: "들여쓰기",
            format: "blockquote"
        }, {
            title: "빨간색",
            inline: "span",
            styles: {
                color: "#ff0000"
            }
        }, {
            title: "녹색",
            block: "span",
            styles: {
                color: "#00ff00"
            }
        }, {
            title: "파란색",
            block: "span",
            styles: {
                color: "#0000ff"
            }
        }],
        height: "650",
        setup: function(e) {
            e["on"]("change", function(e) {
                updateRight()
            }), e["on"]("keyup", function(e) {
                updateRight()
            }), e["on"]("undo", function(e) {
                updateRight()
            }), e["on"]("redo", function(e) {
                updateRight()
            }), e["on"]("focus", function(e) {
                wysiwygActive = 1
            }), e["on"]("blur", function(e) {
                wysiwygActive = 0
            })
        }
    }) : tinymce["init"]({
        selector: "textarea#elm1",
        theme: "modern",
        convert_urls: !1,
        entity_encoding: "raw",
        plugins: ["advlist autolink link image lists charmap print preview hr anchor", "searchreplace visualblocks visualchars code fullscreen insertdatetime media nonbreaking", "table directionality emoticons paste textcolor"],
        add_unload_trigger: !1,
        toolbar: "undo redo | code | removeformat | styleselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons table",
        image_advtab: !0,
        style_formats: [{
            title: "Heading 1",
            format: "h1"
        }, {
            title: "Heading 2",
            format: "h2"
        }, {
            title: "Heading 3",
            format: "h3"
        }, {
            title: "Heading 4",
            format: "h4"
        }, {
            title: "Paragraph",
            format: "p"
        }, {
            title: "들여쓰기",
            format: "blockquote"
        }, {
            title: "빨간색",
            inline: "span",
            styles: {
                color: "#ff0000"
            }
        }, {
            title: "녹색",
            block: "span",
            styles: {
                color: "#00ff00"
            }
        }, {
            title: "파란색",
            block: "span",
            styles: {
                color: "#0000ff"
            }
        }],
        height: "650",
        setup: function(e) {
            e["on"]("change", function(e) {
                updateRight()
            }), e["on"]("keyup", function(e) {
                updateRight()
            }), e["on"]("undo", function(e) {
                updateRight()
            }), e["on"]("redo", function(e) {
                updateRight()
            }), e["on"]("focus", function(e) {
                wysiwygActive = 1
            }), e["on"]("blur", function(e) {
                wysiwygActive = 0
            })
        }
    }), setTimeout(function() {
        tinyMCE["activeEditor"]["focus"](), $("#elm1_ifr")["contents"]()["scroll"](function() {
            setSourceScroll()
        })
    }, 2e3)
}

function clickedOption(e) {
    if (0 == opt[e] ? opt[e] = 1 : opt[e] = 0, 0 == e)
        for (t = 1; t < hanyoption; t++) opt[t] = opt[0];
    for (var t = 0; t < hanyoption; t++) 0 == opt[t] ? document["getElementById"]("optionButton" + t)["style"]["backgroundPosition"] = "10px 0px" : document["getElementById"]("optionButton" + t)["style"]["backgroundPosition"] = "10px -66px";
    for (t = 1; t < hanyoption; t++) createCookie("elmentettszettingek" + t, opt[t], 30);
    16 != e && 0 != e || (tinyMCE["activeEditor"]["remove"](), editortUjraInicializal(), updateLeft(), updateRight())
}

function setDefaultOptions() {
    (opt = [0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0])[1] = 1, opt[2] = 1, opt[3] = 1, opt[4] = 1, opt[16] = 1, opt[6] = 1, opt[10] = 1, opt[13] = 1;
    for (var e = 0; e < hanyoption; e++) 0 == opt[e] ? document["getElementById"]("optionButton" + e)["style"]["backgroundPosition"] = "10px 0px" : document["getElementById"]("optionButton" + e)["style"]["backgroundPosition"] = "10px -66px";
    for (e = 1; e < hanyoption; e++) createCookie("elmentettszettingek" + e, opt[e], 30);
    tinyMCE["activeEditor"]["remove"](), editortUjraInicializal(), updateLeft(), updateRight()
}

function removeTagAttributes() {
    hossz = text["length"];
    for (var e = text["split"](""), t = new Array(""), x = 1, o = 0, i = 0; o < hossz;) e[o] == "<" && (x = 2, e[o + 1] == "!" && e[o + 2] == "-" && e[o + 3] == "-" && (x = 1), e[o + 1] == "a" && e[o + 2] == "" && (x = 4), e[o + 1] == "i" && e[o + 2] == "m" && e[o + 3] == "g" && e[o + 4] == "" && (x = 14)), e[o] == "" && (2 == x && (x = 3), 4 != x && 5 != x || (e[o + 1] == "h" && e[o + 2] == "r" && e[o + 3] == "e" && e[o + 4] == "f" && (x = 6), e[o + 1] == "d" && e[o + 2] == "o" && e[o + 3] == "w" && e[o + 4] == "n" && e[o + 5] == "l" && e[o + 6] == "o" && e[o + 7] == "a" && e[o + 8] == "d" && (x = 6)), 14 != x && 15 != x || e[o + 1] == "s" && e[o + 2] == "r" && e[o + 3] == "c" && (x = 16), 4 == x && (x = 5), 8 == x && (x = 3), 14 == x && (x = 15), 18 == x && (x = 3)), e[o] == "\"" && x == "7" && (x = 8), e[o] == "\"" && x == "6" && (x = 7), e[o] == "\"" && x == "17" && (x = 18), e[o] == "\"" && x == "16" && (x = 17), (e[o] == ">" || e[o] == "/" && e[o + 1] == ">") && (x = 1), 1 != x && 2 != x && 4 != x && 6 != x && 7 != x && 8 != x && 14 != x && 16 != x && 17 != x && 18 != x || (t[i] = e[o], i++), o++;
    text = t["join"]("")
}

function uresTagotTorul() {
    hossz = text["length"];
    for (var e = text["split"](""), t = new Array(""), x = 0, o = 0, i = 0, _ = 0, n = 0; x < hossz;) {
        if (0 == i && e[x] == "<" && e[x + 1] != "/" && (i = 1, _ = x), 2 == i && e[x] == ">") {
            for (n = 0; n <= x - _; n++) t[n + _] = "";
            e[x] = "", i = 0
        }
        1 == i && e[x] == ">" && (i = e[x - 2] != "/" && e[x - 1] != "/" && e[x + 1] == "<" && e[x + 2] == "/" ? 2 : 0), t[o] = e[x], o++, x++
    }
    text = t["join"]("")
}

function csakEnteresTagotTorul() {
    hossz = text["length"];
    for (var e = text["split"](""), t = new Array(""), x = 0, o = 0, i = 0, _ = 0, n = 0; x < hossz;) {
        if (0 == i && e[x] == "<" && e[x + 1] != "/" && (i = 1, _ = x), 2 == i && e[x] == ">") {
            for (n = 0; n <= x - _; n++) t[n + _] = "";
            e[x] = "", i = 0
        }
        1 == i && e[x] == ">" && (i = e[x - 2] != "/" && e[x - 1] != "/" && e[x + 1] == "\n" && e[x + 2] == "<" && e[x + 3] == "/" ? 2 : 0), t[o] = e[x], o++, x++
    }
    text = t["join"]("")
}

function csakEgyNbspTagotTorul() {
    hossz = text["length"];
    for (var e = text["split"](""), t = new Array(""), x = 0, o = 0, i = 0, _ = 0, n = 0; x < hossz;) {
        if (0 == i && e[x] == "<" && e[x + 1] != "/" && (i = 1, _ = x), 2 == i && e[x] == ">") {
            for (n = 0; n <= x - _; n++) t[n + _] = "";
            e[x] = "", i = 0
        }
        1 == i && e[x] == ">" && (i = e[x - 2] != "/" && e[x - 1] != "/" && e[x + 1] == "&" && e[x + 2] == "n" && e[x + 3] == "b" && e[x + 4] == "s" && e[x + 5] == "p" && e[x + 6] == ";" && e[x + 7] == "<" && e[x + 8] == "/" ? 2 : 0), t[o] = e[x], o++, x++
    }
    text = t["join"]("")
}

function torolTagbanKettoKozt(e, t) {
    hossz = text["length"];
    for (var x = e["length"], o = t["length"], i = text["split"](""), _ = e["split"](""), n = t["split"](""), s = 0, a = new Array(""), r = 1, l = 0, c = 0, u = 0, d = 0, p = 0; l < hossz;) {
        if (i[l] == "<" && (s = 1), i[l] == ">" && (s = 0), 1 == s) {
            for (d = 1, u = 0; u < x; u++) _[u] != i[l + u] && (d = 0);
            if (1 == d)
                for (p++, r = -999, l += x, u = 0; u < x; u++) a[c] = _[u], c++
        }
        for (d = 1, u = 0; u < o; u++) n[u] != i[l + u] && (d = 0);
        1 == d && (r = 0), -999 != r && r++, r > 0 && (a[c] = i[l], c++), l++
    }
    return text = a["join"](""), p
}

function tTKKInclusive(e, t) {
    torolTagbanKettoKozt(e, t), helyettesit("eleje.concat(vege)", "")
}

function removeStilust() {
    hossz = text["length"];
    for (var e = text["split"](""), t = new Array(""), x = 1, o = 0, i = 0; o < hossz;) e[o] == "s" && e[o + 1] == "t" && e[o + 2] == "y" && e[o + 3] == "l" && e[o + 4] == "e" && e[o + 5] == "=" && e[o + 6] == "\"" && (x = -999, o += 6), -999 == x && e[o + 1] == "\"" && (x = -2), -999 != x && x++, x > 0 && (t[i] = e[o], i++), o++;
    text = t["join"]("")
}

function helyettesit(e, t) {
    var x = text,
        o = 0,
        i = 0;
    do {
        o = 0, (x = text["replace"](e, t)) == text ? o = 1 : i++, text = x
    } while (0 == o);
    return i
}

function bekezdeseketRendez() {
    var e = 0;
    hossz = text["length"];
    for (var t = text["split"](""), x = new Array(""), o = 0, i = 0, _ = 0, n = 1, s = 0; o < hossz;) {
        if (t[o - 5] == "<" && t[o - 4] == "!" && t[o - 3] == "D" && t[o - 2] == "O" && t[o - 1] == "C" && t[o] == "T" && (s = 1), t[o - 5] == "<" && t[o - 4] == "!" && t[o - 3] == "d" && t[o - 2] == "o" && t[o - 1] == "c" && t[o] == "t" && (s = 1), t[o - 5] == "<" && t[o - 4] == "L" && t[o - 3] == "I" && t[o - 2] == "N" && t[o - 1] == "K" && (s = 1), t[o - 5] == "<" && t[o - 4] == "l" && t[o - 3] == "i" && t[o - 2] == "n" && t[o - 1] == "k" && (s = 1), t[o - 5] == "<" && t[o - 4] == "M" && t[o - 3] == "E" && t[o - 2] == "T" && t[o - 1] == "A" && (s = 1), t[o - 5] == "<" && t[o - 4] == "m" && t[o - 3] == "e" && t[o - 2] == "t" && t[o - 1] == "a" && (s = 1), t[o - 5] == "<" && t[o - 4] == "B" && t[o - 3] == "A" && t[o - 2] == "S" && t[o - 1] == "E" && (s = 1), t[o - 5] == "<" && t[o - 4] == "b" && t[o - 3] == "a" && t[o - 2] == "s" && t[o - 1] == "e" && (s = 1), t[o - 2] == "<" && t[o - 1] == "B" && t[o] == "R" && (s = 1), t[o - 2] == "<" && t[o - 1] == "b" && t[o] == "r" && (s = 1), t[o - 5] == "<" && t[o - 4] == "c" && t[o - 3] == "o" && t[o - 2] == "l" && (s = 1), t[o - 5] == "<" && t[o - 4] == "C" && t[o - 3] == "O" && t[o - 2] == "L" && (s = 1), t[o - 5] == "<" && t[o - 4] == "c" && t[o - 3] == "o" && t[o - 2] == "m" && t[o - 1] == "m" && t[o] == "a" && (s = 1), t[o - 5] == "<" && t[o - 4] == "e" && t[o - 3] == "m" && t[o - 2] == "b" && t[o - 1] == "e" && (s = 1), t[o - 2] == "<" && t[o - 1] == "H" && t[o] == "R" && (s = 1), t[o - 2] == "<" && t[o - 1] == "h" && t[o] == "r" && (s = 1), t[o - 5] == "<" && t[o - 4] == "I" && t[o - 3] == "M" && t[o - 2] == "G" && (s = 1), t[o - 5] == "<" && t[o - 4] == "i" && t[o - 3] == "m" && t[o - 2] == "g" && (s = 1), t[o - 5] == "<" && t[o - 4] == "i" && t[o - 3] == "n" && t[o - 2] == "p" && t[o - 1] == "u" && (s = 1), t[o - 5] == "<" && t[o - 4] == "p" && t[o - 3] == "a" && t[o - 2] == "r" && t[o - 1] == "a" && (s = 1), t[o - 5] == "<" && t[o - 4] == "s" && t[o - 3] == "o" && t[o - 2] == "u" && t[o - 1] == "r" && (s = 1), t[o] == "<" && t[o + 1] == "s" && t[o + 2] == "c" && t[o + 3] == "r" && t[o + 4] == "i" && t[o + 5] == "p" && t[o + 6] == "t")
            for (; t[o - 8] != "/" || t[o - 7] != "s" || t[o - 6] != "c" || t[o - 5] != "r" || t[o - 4] != "i" || t[o - 3] != "p" || t[o - 2] != "t" || t[o - 1] != ">";) x[i] = t[o], i++, o++;
        if (t[o] == "<" && t[o + 1] == "s" && t[o + 2] == "t" && t[o + 3] == "y" && t[o + 4] == "l" && t[o + 5] == "e")
            for (; t[o - 8] != "<" || t[o - 7] != "/" || t[o - 6] != "s" || t[o - 5] != "t" || t[o - 4] != "y" || t[o - 3] != "l" || t[o - 2] != "e" || t[o - 1] != ">";) x[i] = t[o], i++, o++;
        if (t[o - 3] == "<" && t[o - 2] == "!" && t[o - 1] == "-" && t[o] == "-" && 1, t[o - 3] == "-" && t[o - 2] == "-" && t[o - 1] == ">" && 0, n = 1, t[o] == "<" && t[o + 1] != "!")
            if (t[o + 1] == "/") {
                for (e--, x[i] = "\n", i++, _ = 0; _ < e; _++) x[i] = "	", i++;
                e--
            } else
                for (x[i] = "\n", i++, _ = 0; _ < e; _++) x[i] = "	", i++;
        if (t[o] == ">" && t[o - 1] != "-" && t[o - 1] != "]")
            if (t[o - 1] == "/")
                for (n = 0, s = 0, x[i] = ">", x[i + 1] = "\n", i += 2, _ = 0; _ < e; _++) x[i] = "	", i++;
            else
                for (n = 0, 0 == s && e++, s = 0, x[i] = ">", x[i + 1] = "\n", i += 2, _ = 0; _ < e; _++) x[i] = "	", i++;
        1 == n && (x[i] = t[o], i++), o++
    }
    if (x[0] == "\n") {
        for (o = 0; o < i; o++) x[o] = x[o + 1];
        x[o + 1] = ""
    }
    text = x["join"]("")
}

function convertText() {
    undotext = tinymce["get"]("elm1")["getContent"](), text = undotext;
    var e = 0;
    helyettesit("	", ""), helyettesit("", ""), helyettesit("\n", "\n"), helyettesit("	\n", "\n"), helyettesit("\n\n", "\n"), helyettesit("", "");
    var t = 0,
        x = 0;
    torolTagbanKettoKozt("<script", "</script>"), torolTagbanKettoKozt("<style", "</style>"), helyettesit("<style</style>", "") > 0 && t++, helyettesit("<script</script>", "") > 0 && x++, 0 != t && 0 == x && popupBox("Inline styles have been removed. Include these as seperate .ccs files if needed!"), 0 != t && 0 != x && popupBox("Inline styles and scripts have been removed. Include these as seperate files if needed!"), 0 == t && 0 != x && popupBox("Inline scripts have been removed. Include these as seperate files if needed!"), 1 == opt[12] && (helyettesit("<!--", "&%&%&%&%&%!--"), torolTagbanKettoKozt("<", ">"), helyettesit("<>", ""), helyettesit("&%&%&%&%&%!--", "<!--")), 1 == opt[1] && (helyettesit("style =", "style="), helyettesit("style=", "style="), helyettesit("style =", "style="), torolTagbanKettoKozt("style=\"", "\""), helyettesit("style=\"\"", ""), helyettesit("valign =", "valign="), helyettesit("valign=", "valign="), helyettesit("valign =", "valign="), torolTagbanKettoKozt("valign=\"", "\""), helyettesit("valign=\"\"", ""), helyettesit("align =", "align="), helyettesit("align=", "align="), helyettesit("align =", "align="), torolTagbanKettoKozt("align=\"", "\""), helyettesit("align=\"\"", "")), 1 == opt[2] && (helyettesit("&nbsp;&nbsp;", ""), helyettesit("&nbsp;", ""), helyettesit("&nbsp;", "")), 1 == opt[3] && (helyettesit("class =", "class="), helyettesit("class=", "class="), helyettesit("class =", "class="), torolTagbanKettoKozt("class=\"", "\""), helyettesit("class=\"\"", ""), helyettesit("id =", "id="), helyettesit("id=", "id="), helyettesit("id =", "id="), torolTagbanKettoKozt("id=\"", "\""), helyettesit("id=\"\"", "")), 1 == opt[4] && (torolTagbanKettoKozt("<i", ">"), torolTagbanKettoKozt("<b", ">"), helyettesit("<i>", "<em>"), helyettesit("<i >", "<em>"), helyettesit("</i>", "</em>"), helyettesit("<b>", "<strong>"), helyettesit("<b >", "<strong>"), helyettesit("</b>", "</strong>")), 1 == opt[7] && (torolTagbanKettoKozt("<table", ">"), torolTagbanKettoKozt("<thead", ">"), torolTagbanKettoKozt("<tfoot", ">"), torolTagbanKettoKozt("<tbody", ">"), torolTagbanKettoKozt("<td", ">"), torolTagbanKettoKozt("<tr", ">"), torolTagbanKettoKozt("<th", ">"), helyettesit("<table>", ""), helyettesit("<thead>", ""), helyettesit("<tfoot>", ""), helyettesit("<tbody>", ""), helyettesit("<td>", ""), helyettesit("<tr>", ""), helyettesit("<th>", ""), helyettesit("</table>", ""), helyettesit("</thead>", ""), helyettesit("</tfoot>", ""), helyettesit("</tbody>", ""), helyettesit("</td>", ""), helyettesit("</tr>", ""), helyettesit("</th>", "")), 1 == opt[8] && (torolTagbanKettoKozt("<table", ">"), torolTagbanKettoKozt("<thead", ">"), helyettesit("<thead>", "<div class=\"rTableHeading\">"), torolTagbanKettoKozt("<tfoot", ">"), torolTagbanKettoKozt("<tbody", ">"), torolTagbanKettoKozt("<td", ">"), torolTagbanKettoKozt("<tr", ">"), torolTagbanKettoKozt("<th", ">"), helyettesit("<table>", "<div class=\"rTable\">"), helyettesit("<tbody>", "<div class=\"rTableBody\">"), helyettesit("<tfoot>", "<div class=\"rTableFoot\">"), helyettesit("<td>", "<div class=\"rTableCell\">"), helyettesit("<tr>", "<div class=\"rTableRow\">"), helyettesit("<th>", "<div class=\"rTableHead\">"), helyettesit("</table>", "</div>"), helyettesit("</tbody>", "</div>"), helyettesit("</thead>", "</div>"), helyettesit("</tfoot>", "</div>"), helyettesit("</td>", "</div>"), helyettesit("</tr>", "</div>"), helyettesit("</th>", "</div>")), 1 == opt[9] && (torolTagbanKettoKozt("<a", ">"), helyettesit("<a >", ""), helyettesit("<a>", ""), helyettesit("</a>", "")), 1 == opt[10] && (torolTagbanKettoKozt("<span", ">"), helyettesit("<span >", ""), helyettesit("<span>", ""), helyettesit("</span>", "")), 1 == opt[11] && (torolTagbanKettoKozt("<img", ">"), helyettesit("<img >", ""), helyettesit("<img>", ""), helyettesit("</img>", "")), 1 == opt[13] && (torolTagbanKettoKozt("<!--", "-->"), helyettesit("<!---->", "")), 1 == opt[6] && (helyettesit("> &nbsp;<", ">&nbsp;<"), helyettesit(">&nbsp; <", ">&nbsp;<"), csakEgyNbspTagotTorul()), 1 == opt[5] && (helyettesit("> <", "><"), helyettesit("> \n", ">\n"), uresTagotTorul(), csakEnteresTagotTorul());
    do {
        e = 0, e += helyettesit("", ""), e += helyettesit(">", ">"), e += helyettesit("	", ""), e += helyettesit("", ""), e += helyettesit("&nbsp;\n", "\n"), e += helyettesit("\n", "\n"), e += helyettesit("\n\n", "\n"), 1 == opt[14] && (e += helyettesit("\n", ""))
    } while (e > 0);
    1 == opt[15] && removeTagAttributes(), 1 == opt[14] && (bekezdeseketRendez(), helyettesit("\n", "\n"), helyettesit("	\n", "\n"), helyettesit("\n\n", "\n"), helyettesit("", "")), helyettesit("\n", "\n"), helyettesit("	\n", "\n"), helyettesit("\n\n", "\n"), helyettesit("", ""), -1 == String(document["domain"])["indexOf"]("ml-on") && (text = ""), document["getElementById"]("onTheConvertButton")["style"]["backgroundPosition"] = "-175px -25px", document["body"]["style"]["cursor"] = "default", sourceEditorFricc["setValue"](text), tinyMCE["activeEditor"]["setContent"](text), document["getElementById"]("inputLength")["innerHTML"] = "Source:" + text["length"], $(".programIsThinking")["fadeOut"](300)
}

function findandreplacenow() {
    undotext = tinymce["get"]("elm1")["getContent"](), text = undotext, replaceaktiv[1] && replacetext1["value"] != "" && helyettesit(replacetext1["value"], replacewith1["value"]), replaceaktiv[2] && replacetext2["value"] != "" && helyettesit(replacetext2["value"], replacewith2["value"]), replaceaktiv[3] && replacetext3["value"] != "" && helyettesit(replacetext3["value"], replacewith3["value"]), replaceaktiv[4] && replacetext4["value"] != "" && helyettesit(replacetext4["value"], replacewith4["value"]), replaceaktiv[5] && replacetext5["value"] != "" && helyettesit(replacetext5["value"], replacewith5["value"]), replaceaktiv[6] && replacetext6["value"] != "" && helyettesit(replacetext6["value"], replacewith6["value"]), replaceaktiv[7] && replacetext7["value"] != "" && helyettesit(replacetext7["value"], replacewith7["value"]), replaceaktiv[8] && replacetext8["value"] != "" && helyettesit(replacetext8["value"], replacewith8["value"]), replaceaktiv[9] && replacetext9["value"] != "" && helyettesit(replacetext9["value"], replacewith9["value"]), replaceaktiv[10] && replacetext10["value"] != "" && helyettesit(replacetext10["value"], replacewith10["value"]), replaceaktiv[11] && replacetext11["value"] != "" && helyettesit(replacetext11["value"], replacewith11["value"]), replaceaktiv[12] && replacetext12["value"] != "" && helyettesit(replacetext12["value"], replacewith12["value"]), sourceEditorFricc["setValue"](text), tinyMCE["activeEditor"]["setContent"](text), document["getElementById"]("inputLength")["innerHTML"] = "Source:" + text["length"], $(".overlayContainer")["fadeOut"](200), $(".replaceList")["fadeOut"](200)
}

function generateLipsum() {
    //undotext = sourceEditorFricc["getValue"]();
    //var e = document["szettingform"]["hanyparagrafuslegyen"]["value"],
    //    t = "\n<p>Lorem ipsum dolor sit amet, nonumes voluptatum mel ea, cu case ceteros cum. Novum commodo malorum vix ut. Dolores consequuntur in ius, sale electram dissentiunt quo te. Cu duo omnes invidunt, eos eu mucius fabellas. Stet facilis ius te, quando voluptatibus eos in. Ad vix mundi alterum, integre urbanitas intellegam vix in.</p>";
    //e > 1 && (t += "\n<p>Eum facete intellegat ei, ut mazim melius usu. Has elit simul primis ne, regione minimum id cum. Sea deleniti dissentiet ea. Illud mollis moderatius ut per, at qui ubique populo. Eum ad cibo legimus, vim ei quidam fastidii.</p>"), e > 2 && (t += "\n<p>Quo debet vivendo ex. Qui ut admodum senserit partiendo. Id adipiscing disputando eam, sea id magna pertinax concludaturque. Ex ignota epicurei quo, his ex doctus delenit fabellas, erat timeam cotidieque sit in. Vel eu soleat voluptatibus, cum cu exerci mediocritatem. Malis legere at per, has brute putant animal et, in consul utamur usu.</p>"), e > 3 && (t += "\n<p>Te has amet modo perfecto, te eum mucius conclusionemque, mel te erat deterruisset. Duo ceteros phaedrum id, ornatus postulant in sea. His at autem inani volutpat. Tollit possit in pri, platonem persecuti ad vix, vel nisl albucius gloriatur no.</p>"), e > 4 && (t += "\n<p>Ea duo atqui incorrupte, sed rebum regione suscipit ex, mea ex dicant percipit referrentur. Dicat luptatum constituam vix ut. His vide platonem omittantur id, vel quis vocent an. Ad pro inani zril omnesque. Mollis forensibus sea an, vim habeo adipisci contentiones ad, tale autem graecis ne sit.</p>"), e > 5 && (t += "\n<p>Ex quod meis per, ea paulo eirmod intellegam usu, eam te propriae fabellas. Nobis graecis has at, an eum audire impetus. Ius epicuri verterem ex, qui cu solet feugiat consetetur. Placerat apeirian et sea, nec wisi viderer definiebas ex, at eum oratio honestatis.</p>"), e > 6 && (t += "\n<p>Eum illum nulla graeci at, mea quis munere indoctum at. In sea partiendo hendrerit. Quaestio partiendo an eam, rebum vitae accumsan ius id. Duo at causae option.</p>"), e > 7 && (t += "\n<p>At persius imperdiet vis, ea elit atqui aperiri mei, percipit maiestatis sea eu. Has et partem hendrerit, vim cibo veniam aliquid an. No pri populo abhorreant, everti mandamus ne mea. Debitis forensibus suscipiantur ius cu. Ei per possim verterem, et iudico voluptatum eos.</p>"), e > 8 && (t += "\n<p>Te mel meis adhuc. Choro percipit mei eu, fabulas fuisset tibique ad sea, cu eos sint falli iracundia. Usu ex minimum corrumpit, postea dolores salutandi ne est, cu nam option recusabo reprehendunt. Prima vocibus argumentum ex usu. Nam te legere salutatus dissentiunt, his ei principes prodesset, est possit blandit ex.</p>"), e > 9 && (t += "\n<p>Pro no rebum timeam necessitatibus, et mnesarchum quaerendum has. Duo molestie interesset at. Vel ad legere populo. Sed ne saepe doming perpetua. Omnis iuvaret volumus an duo, qui duis audiam fabellas in.</p>"), e > 10 && (t += "\n<p>Has in erant eruditi vituperatoribus, facer copiosae nam ex. Ne quo error rationibus, cum ea accusam comprehensam. Quo no nihil rationibus intellegam. Duo ea justo deleniti tincidunt, per et erant volumus consequat, per simul consulatu ne. Efficiendi contentiones mel id, ad quaeque facilis vel. Ius mutat nullam ut, dolores officiis platonem qui ex.</p>"), e > 11 && (t += "\n<p>Et cum quem movet nonumes, at molestie mandamus intellegebat eum, at habeo vulputate vel. An nec diam consequuntur, quo an diam numquam theophrastus. Et unum possit sit. Suas ludus sea an. Quo expetendis consetetur an, no perpetua consequuntur cum. Congue tritani delenit eam an. Porro albucius id mei, ut fabellas scripserit interesset vis.</p>"), e > 12 && (t += "\n<p>Eos causae albucius deseruisse ea, mel augue eirmod convenire no. Ad volutpat consulatu definiebas sea, nec integre scribentur ei. Principes mnesarchum mea ei, dicam laboramus abhorreant an has. Mutat iuvaret pri ea, id quo reque libris deseruisse. Ut aeterno denique minimum duo, indoctum reformidans id pro.</p>"), e > 13 && (t += "\n<p>Alii dissentias eu eum, ei vix exerci laudem placerat. Ius at sonet saepe theophrastus, atqui qualisque urbanitas no sit, aperiam vulputate sadipscing te eam. An dolor mediocrem patrioque qui, his propriae laboramus scripserit in, ne ubique tamquam has. Tempor aperiri nominati vis eu. At recteque gloriatur eum, viris referrentur sit ut, et tota solum pri.</p>"), e > 14 && (t += "\n<p>Quidam lobortis intellegat ea mel. Aeterno facilis mea ne. Omittam periculis no eos, duo cu sale autem ullum, legere tritani ut eos. In vix adhuc facer. Nec erat commodo efficiendi eu. Et nusquam lucilius splendide pro, at nostro constituam appellantur sea.</p>"), e > 15 && (t += "\n<p>Ei iriure fastidii sea, vim ut vivendum pertinacia. Cu dolor perpetua his, debet doctus definitiones an sit. Velit ridens propriae in vix. Qui ut justo essent lucilius, no choro doming pro.</p>"), e > 16 && (t += "\n<p>Est in eros contentiones, te dicit explicari tincidunt duo. No duo vocent perpetua salutatus, his ut essent placerat. Cu error argumentum sea. In sed eirmod veritus, te mundi utamur per. Ne sit case inani tollit, ea mel autem partiendo. Eum solum animal alterum ei. Sea quem oportere eu.</p>"), e > 17 && (t += "\n<p>At ius ipsum prodesset. Pro menandri evertitur constituam ut, in elit porro repudiare usu, nusquam praesent comprehensam est te. Autem mnesarchum nam te. Libris qualisque nam an. Ei nam diam putant facilis. Quis deleniti no vel, liber perfecto ei cum, vim ea inermis imperdiet.</p>"), e > 18 && (t += "\n<p>Id vide albucius lobortis pri. Mel saperet habemus scriptorem eu, harum pertinax euripidis vel ne. Dolor accumsan reprimique sit an. Ad veri vitae fuisset cum, nostrum gloriatur vix id. Perfecto voluptatum at mel, te ius dicant vituperatoribus.</p>"), e > 19 && (t += "\n<p>Sed ne appellantur ullamcorper. Dicant persius quaestio et per, assum tritani omittantur vix no, vix suas aliquip ut. Per meis rationibus ut, eum ut delicata laboramus. An vix brute eruditi ocurreret, ex nec animal omnesque assentior, voluptatibus conclusionemque usu ne. Quo perpetua argumentum referrentur ei, cum idque phaedrum at. Quodsi instructior voluptatibus pri ex. Nec munere ornatus ut, mediocrem partiendo eu nam.</p>"), 1 == document["szettingform"]["mitpupulaljon"]["value"] ? (sourceEditorFricc["setValue"](undotext + t), tinyMCE["activeEditor"]["setContent"](undotext + t), document["getElementById"]("inputLength")["innerHTML"] = "Source:" + text["length"]) : (document["getElementById"]("lipszuTextarea")["innerHTML"] = t, $("#lipszuTextarea")["fadeIn"](300)), $(".overlayContainer")["fadeOut"](200), $(".lipsumText")["fadeOut"](200), $(".tagManager")["fadeOut"](200), $(".colorPicker")["fadeOut"](200)
}

function popupokatLevesz() {
    $(".overlayContainer")["fadeOut"](200), $(".settingsList")["fadeOut"](200), $(".replaceList")["fadeOut"](200), $(".lipsumText")["fadeOut"](200), $(".tagManager")["fadeOut"](200), $(".colorPicker")["fadeOut"](200), document["getElementById"]("myColor")["color"]["hidePicker"]()
}

function updateColor() {
    selectedColor = document["getElementById"]("myColor")["value"];
    "A szin neve";
    colr = parseInt((selectedColor["charAt"](0) + selectedColor["charAt"](1)).toString(16), 16), colg = parseInt((selectedColor["charAt"](2) + selectedColor["charAt"](3)).toString(16), 16), colb = parseInt((selectedColor["charAt"](4) + selectedColor["charAt"](5)).toString(16), 16), rgb2cmyk(colr, colg, colb);
    "";
    ovlashato = $("#myColor")["css"]("color"), document["getElementById"]("selectedbackgr")["innerHTML"] = "<div class=\"szinInfok\" style=\"color:" + ovlashato + "\"><span class=\"uppercase\">#" + selectedColor + "</span><br />rgb(" + colr + "," + colg + "," + colb + ")<br />CMYK(" + colc + "," + colm + "," + coly + "," + colk + ") <div class=\"saveOrLink\"><a id=\"addToPalette\" style=\"color:#" + selectedColor + ";background-color:" + ovlashato + "\" onClick = \"addtopalette();\" title=\"Add to palette\">Save</a></div></div>", document["getElementById"]("openColorLink")["innerHTML"] = "<a class=\"buttonka\" href=\"https://rgbcolorcode.com/color/" + selectedColor + "\" target=\"_blank\" title=\"Open #" + selectedColor + "in the color mixer\">Color Mixer</a>", document["getElementById"]("selectedbackgr")["style"]["backgroundColor"] = "#" + selectedColor, $("#colorToUse>div")["css"]("border-top", "3px solid #" + selectedColor), document["getElementById"]("colorToUse")["innerHTML"] = "<h5>Click and Copy:</h5><input onClick=\"this.select();\" value=\"color:#" + selectedColor + ";\" /><input onClick=\"this.select();\" value=\"style=&quot;color:#" + selectedColor + ";&quot;\" /><input onClick=\"this.select();\" value=\"background-color:#" + selectedColor + ";\" /><input onClick=\"this.select();\" value=\"style=&quot;background-color:#" + selectedColor + ";&quot;\" /><input onClick=\"this.select();\" value=\"border: 3px solid #" + selectedColor + ";\"/ ><input onClick=\"this.select();\" value=\"text-shadow: 1px 2px 2px #" + selectedColor + ";\" / ><input onClick=\"this.select();\" value=\"box-shadow: 2px 2px 7px 1px #" + selectedColor + ";\" / >"
}

function aplikal(e) {
    document["getElementById"]("myColor")["value"] = e, document["getElementById"]("myColor")["color"]["fromString"](e), updateColor(e)
}

function rgb2cmyk(e, t, x) {
    var o = 0,
        i = 0,
        _ = 0,
        n = 0,
        e = parseInt(("" + e)["replace"](/\s/g, ""), 10),
        t = parseInt(("" + t)["replace"](/\s/g, ""), 10),
        x = parseInt(("" + x)["replace"](/\s/g, ""), 10);
    if (null == e || null == t || null == x || isNaN(e) || isNaN(t) || isNaN(x)) alert("Please enter numeric RGB values!");
    else {
        if (!(e < 0 || t < 0 || x < 0 || e > 255 || t > 255 || x > 255)) {
            if (0 == e && 0 == t && 0 == x) return n = 1, [0, 0, 0, 1];
            o = 1 - e / 255, i = 1 - t / 255, _ = 1 - x / 255;
            var s = Math["min"](o, Math["min"](i, _));
            return o = (o - s) / (1 - s), i = (i - s) / (1 - s), _ = (_ - s) / (1 - s), n = s, colc = Math["round"](1e4 * o) / 1e4, colm = Math["round"](1e4 * i) / 1e4, coly = Math["round"](1e4 * _) / 1e4, colk = Math["round"](1e4 * n) / 1e4, [o, i, _, n]
        }
        alert("RGB values must be in the range 0 to 255.")
    }
}

function getRandomBetween(e, t) {
    return Math["round"](Math["random"]() * (t - e) + e)
}

function addtopalette() {
    document["getElementById"]("savedCodes")["innerHTML"] = document["getElementById"]("savedCodes")["innerHTML"] + "<div class=\"savedPalette\" style=\"background-color:#" + selectedColor + "\"><div onclick=\"aplikal('" + selectedColor + "');\" class=\"aplikalo\" title=\"Activate\">&#10138;</div><input onClick=\"this.select();\" value=#" + selectedColor + "/> </div>"
}

function RGB2HSV(e) {
    return hsv = new Object, max = max3(e["r"], e["g"], e["b"]), dif = max - min3(e["r"], e["g"], e["b"]), hsv["saturation"] = 0 == max ? 0 : 100 * dif / max, 0 == hsv["saturation"] ? hsv["hue"] = 0 : e["r"] == max ? hsv["hue"] = 60 * (e["g"] - e["b"]) / dif : e["g"] == max ? hsv["hue"] = 120 + 60 * (e["b"] - e["r"]) / dif : e["b"] == max && (hsv["hue"] = 240 + 60 * (e["r"] - e["g"]) / dif), hsv["hue"] < 0 && (hsv["hue"] += 360), hsv["value"] = Math["round"](100 * max / 255), hsv["hue"] = Math["round"](hsv["hue"]), hsv["saturation"] = Math["round"](hsv["saturation"]), colhue = hsv["hue"], colsat = hsv["saturation"], colval = hsv["value"], hsv
}

function calledEachHalfSecond() {
    2 == sourceScrolled && (sourceScrolled = 0), 1 == sourceScrolled && (sourceScrolled = 2), 2 == visualScrolled && (visualScrolled = 0), 1 == visualScrolled && (visualScrolled = 2), setTimeout(function() {
        calledEachHalfSecond()
    }, 500)
}

function setVisualScroll(e) {
    if (0 != e && (sourceEditorObject = e), 0 == sourceScrolled && 1 == scrollEditorsTogether && 0 != e) {
        var t = e["scrollTop"],
            x = e["scrollHeight"] - e["clientHeight"],
            o = $("#elm1_ifr")["contents"]()["height"](),
            i = $("#elm1_ifr")["height"](),
            _ = Math["round"]((o - i) * t / x);
        $("#elm1_ifr")["contents"]()["scrollTop"](_), visualScrolled = 1
    }
}

function setSourceScroll() {
    if (0 == visualScrolled && 1 == scrollEditorsTogether) {
        var e = $("#elm1_ifr")["contents"]()["scrollTop"](),
            t = $("#elm1_ifr")["contents"]()["height"]() - $("#elm1_ifr")["height"](),
            x = $(".szekcio3 .CodeMirror-sizer")["height"]() - $(".szekcio3 .CodeMirror-scroll")["height"](),
            o = Math["round"](x * e / t);
        $(".szekcio3 .CodeMirror-scroll")["scrollTop"](o), sourceScrolled = 1
    }
}
var hanyoption = 17,
    hanyadikclean = 0,
    opt = new Array,
    text = "duma",
    undotext = "",
    beforeDemoText = "yo",
    replaceaktiv = new Array,
    wysiwygActive = 0,
    scrollEditorsTogether = 11,
    demoElinditva = 0,
    sourceFontSize = 12,
    useBootstrp = 0,
    friday = 0,
	visualScrolled = 0,
	sourceScrolled = 0,
	sourceEditorObject = 0;

$(function() {
    $(".CodeMirror")["resizable"]({
        ghost: !0,
        animate: !0,
        minHeight: 70,
        minWidth: 50
    })
});