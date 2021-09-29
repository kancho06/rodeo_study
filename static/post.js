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

function isValidusername(username) {
    if (username == '') {
        alert('닉네임을 입력해주세요');
        return false;
    }
    if (username.trim().length > 30) {
        alert('공백 포함 30자 이하로 입력해주세요');
        return false;
    }
    return true;
}

function isValidtitle(title) {
    if (title == '') {
        alert('제목을 입력해주세요');
        return false;
    }
    if (title.trim().length > 1000) {
        alert('공백 포함 1000자 이하로 입력해주세요');
        return false;
    }
    return true;
}
function isValidcategory(category) {
    if (category == '') {
        alert('비밀주제를 설정해주세요');
        return false;
    }

}





function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let title = $('#title').val();
    let username = $('#username').val();
    let contents = $('#contents').val();
    let category = $('#category').val();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidtitle(title) == false) {
        return;
    }
    if (isValidusername(username) == false) {
        return;
    }
    if (isValidcategory(category) == false) {
        return;
    }
    if (isValidContents(contents) == false) {
        return;
    }
    // 3. genRandomName 함수를 통해 익명의 username을 만듭니다.

    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'title': title, 'username': username, 'contents': contents, 'category':category};
    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/api/logs",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.replace('/index.html');
        }
    });
}