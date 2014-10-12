/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#NovaVenda").hide(0.0000001);

    $("#BotaoNovo").click(function () {
        $('#BotaoEfetuadas').removeClass("active");
        $('#BotaoNovo').addClass("active");
        $("#VendasEfetuadas").hide(0.0000001);
        $("#NovaVenda").fadeIn('slow');
    });

    $("#BotaoEfetuadas").click(function () {
        $('#BotaoNovo').removeClass("active");
        $('#BotaoEfetuadas').addClass("active");
        $("#NovaVenda").hide(0.0000001);
        $("#VendasEfetuadas").fadeIn('slow');
    });

    $('#TableProdutos').dataTable({
        info: false,
        ordering: false,
        "language": {
            "lengthMenu":     "Mostrar _MENU_ produtos",
            "infoEmpty":      "Não há produtos cadastrados.",
            "search":         "Filtre um produto:",
            "paginate": {
                "first":      "Primeira",
                "last":       "Ultima",
                "next":       "Próxima",
                "previous":   "Anterior"
            }
        }
    });
});