// GET request
function loadGetMsg() {
    let nameVar = document.getElementById("nameGet").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        let resp = JSON.parse(this.responseText);
        document.getElementById("getrespmsg").innerHTML = resp.name;
    }
    xhttp.open("GET", "/app/getInfo?name=" + encodeURIComponent(nameVar));
    xhttp.send();
}

// POST request
function loadPostMsg() {
    let nameVar = document.getElementById("namePost").value;
    fetch("/app/postInfo?name=" + encodeURIComponent(nameVar), {method: 'GET'}) // o POST si lo adaptas
        .then(res => res.json())
        .then(data => {
            document.getElementById("postrespmsg").innerHTML = data.status;
        });
}
