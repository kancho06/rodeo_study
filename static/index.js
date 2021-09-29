

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 여기서부터 코드를 작성해주시면 됩니다.

$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getLog();


})

// 메모를 불러와서 보여줍니다.
function getLog() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#post-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/logs',
        success: function (response) {
            for (i = 0; i < response.length; i++) {
                let log = response[i]
                let id = log.id;
                let title = log.title;
                let username = log.username;
                let modifiedAt = log.modifiedAt;
                let category = log.category;
                // let image = log.image;
                addHTML(id, title, username, modifiedAt, category)
            }
            console.log(response);
        }
    })


}


// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, title, username, modifiedAt, category) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `
<tr>
<td><input class="uk-checkbox" type="checkbox"></td>
                <td><img class="uk-preserve-width uk-border-circle" src="chars/8.ico" width="40" alt=""></td>
                <td class="uk-table-link">
                    <a class="uk-link-reset" href="detail.html?id=${id}" id="${id}-title">${title}</a>
                </td>
                <td class="uk-text-truncate"id="${id}-category">${category}</td>
                <td class="uk-text-nowrap"id="${id}-username">${username}</td>
                <td class="uk-text-nowrap"id="${id}-modifiedAt">${modifiedAt}</td>
            </tr>
`;
    // 2. #cards-box 에 HTML을 붙인다.

    $('#post-box').append(tempHtml);
}


