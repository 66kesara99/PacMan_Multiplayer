"use strict";
function register() {
    const chats = document.getElementById('room');
    
    new EventSource('chat').onmessage = function (e) {
        chats.innerHTML += e.data + '<br>';
        console.log('Received '+ e.data);
    };

    const message = document.getElementById('message');
    const  xhr = new XMLHttpRequest();  
    
    document.getElementById('chat').onsubmit = function (e) {
        e.preventDefault();
        xhr.open('POST', 'chat');
        xhr.setRequestHeader('Content-Type','text/plain');
        xhr.send(message.value);
        console.log('Sent '+ message.value);
        message.value ='';
        return false;
    };
}