jQuery(document).ready(function () {

    $(".chat-list a").click(function () {
        $(".chatbox").addClass('showbox');
        return false;
    });

    $(".chat-icon").click(function () {
        $(".chatbox").removeClass('showbox');
    });
});

document.getElementById('inlineFormInputGroup').addEventListener('input', function () {
    let searchQuery = this.value.toLowerCase();
    let chatItems = document.querySelectorAll('.chat-item');

    chatItems.forEach(function (item) {
        let userName = item.querySelector('h3').textContent.toLowerCase();
        if (userName.includes(searchQuery)) {
            item.classList.remove('hidden');
        } else {
            item.classList.add('hidden');
        }
    });
});
