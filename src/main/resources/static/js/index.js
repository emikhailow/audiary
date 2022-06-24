angular.module('app', []).controller('indexController', function ($rootScope, $http) {

    $rootScope.init = function () {
        navigator.mediaDevices.getUserMedia({audio: true})
            .then(stream => {
                const mediaRecorder = new MediaRecorder(stream);

                document.querySelector('#record').addEventListener('mousedown', function () {
                    mediaRecorder.start();
                });
                let audioChunks = [];
                mediaRecorder.addEventListener("dataavailable", function (event) {
                    audioChunks.push(event.data);
                });

                document.querySelector('#record').addEventListener('mouseup', function () {
                    mediaRecorder.stop();
                });

                mediaRecorder.addEventListener("stop", function () {
                    const audioBlob = new Blob(audioChunks, {
                        type: 'audio/wav'
                    });

                    let fd = new FormData();
                    fd.append('voice', audioBlob);
                    $rootScope.sendVoice(fd);
                    audioChunks = [];
                });
            });

    };

    $rootScope.sendVoice = function (dataForm) {
        fetch('http://localhost:8181/audiary/save', {
            method: 'POST',
            body: dataForm,
            mode: 'no-cors'
        })
            .then(
                function successCallback(response) {
                    $rootScope.loadRecords();
                },
                function errorCallback(response) {
                }
                );
    }

    $rootScope.loadRecords = function () {
        $http.get('http://localhost:8181/audiary/records')
            .then(function (response) {
               $rootScope.RecordsList = response.data;
            });

    };

    $rootScope.deleteRecord = function (id) {
        $http({
            url: 'http://localhost:8181/audiary/records/' + id,
            method: 'DELETE'
        }).then(function (response) {
            $rootScope.loadRecords();
        });
    };

    $rootScope.init();
    $rootScope.loadRecords();

});