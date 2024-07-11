 document.addEventListener("DOMContentLoaded", function() {
        var editButtons = document.querySelectorAll("img[id^='Editar']");
        
        editButtons.forEach(function(button) {
            button.addEventListener("click", function() {
                var id = button.id.replace('Editar', '');
                var paragraph = document.getElementById("informacion" + id);
                
                if (paragraph.tagName.toLowerCase() === "p") {
                    var text = paragraph.innerText;
                    var textarea = document.createElement("textarea");
                    textarea.value = text;
                    textarea.style.width = "100%";
                    textarea.id = "informacion" + id;
                    paragraph.parentNode.replaceChild(textarea, paragraph);
                } else if (paragraph.tagName.toLowerCase() === "textarea") {
                    var newParagraph = document.createElement("p");
                    newParagraph.id = "informacion" + id;
                    newParagraph.innerText = paragraph.value;
                    paragraph.parentNode.replaceChild(newParagraph, paragraph);
                }
            });
        });
    });