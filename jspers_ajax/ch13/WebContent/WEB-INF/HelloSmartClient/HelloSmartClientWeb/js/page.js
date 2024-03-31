Event.observe(window, "load", function() {
    Event.observe("btnPage", "click", function() {
        helloSmartClient.CurrentPage = parseInt($("txtPage").value);
    });
});