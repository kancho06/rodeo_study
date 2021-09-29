$(document).ready(function () {
    getOneLog();

});

function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents.trim().length > 1000) {
        alert('공백 포함 1000자 이하로 입력해주세요');
        return false;
    }
    return true;
}

function editPost(id) {
    showEdits(id);
    let contents = $(`#${id}-contents`).text().trim();
    $(`#${id}-textarea`).val(contents);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
}


function getOneLog() {
    $('#article-box').empty();

    let idx = location.href.split("id=")[1];
    $.ajax({
        type: 'GET',
        url: `/api/logs/${idx}`,
        success: function (response) {
            console.log(response);
            let log = response;
            let id = log.id;
            let title = log.title;
            let category = log.category
            let username = log.username;
            let contents = log.contents;
            let modifiedAt = log.modifiedAt;
            addOneLog(id, title, username, contents, modifiedAt, category);

        }
    })
}

function addOneLog(id, title, username, contents, modifiedAt, category) {
    let tempHtml = `    <h2 class="uk-article-title"><a class="uk-link-reset" href="" id="${id}-title">${title}</a></h2>
                        <br>
    <br>
    <p class="uk-article-meta">Written by <a href="#"id="${id}-username">${username}</a> 비밀주제: <a href="https://ko.wikipedia.org/wiki/${category}" id="${id}-category">${category}</a></p>
        <br>
    <br>
    <p class="uk-text-lead"id="${id}-contents">${contents}</p>
    <div id="${id}-editarea" class="edit">
                        <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                    </div>
    <br>
    
    <p>${modifiedAt}</p>

    <div class="uk-grid-small uk-child-width-auto" uk-grid>
        <div>
            <a class="uk-button uk-button-text" href="#">Read more</a>
        </div>
        <div>
            <a class="uk-button uk-button-text" href="#">5 Comments</a>
        </div>
        <a class="uk-icon-link" uk-icon="icon: file-edit; ratio: 2" onclick="editPost('${id}')" id="${id}-edit"></a>
            <span class="uk-icon-button" uk-icon="icon: check; ratio: 2" onclick="submitEdit(${id})" id="${id}-submit"></span>
            <a class="uk-icon-link" uk-icon="icon: trash; ratio: 2" onclick="deleteOne('${id}')" id="${id}-delete"></a>
                <a href="index.html" class="uk-icon-link uk-margin-small-right" uk-icon="icon: close; ratio: 2"></a>
                
    </div>`
    $('#article-box').append(tempHtml);

}

function deleteOne(id) {
    $.ajax({
        type: "DELETE",
        url: `/api/logs/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.replace("index.html");
        }
    })
}

function submitEdit(id) {
    // 1. 작성 대상 메모의 username과 contents 를 확인합니다.
    let contents = $(`#${id}-textarea`).val().trim();

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'contents': contents};

    // 4. PUT /api/memos/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/api/logs/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.replace("index.html");
        }
    });
}


