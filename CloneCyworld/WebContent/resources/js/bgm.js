/*
function nextMusic() {
   var title=document.querySelector('input[name=title]').value;
   var artist=document.querySelector('input[name=artist]').value;
   var youtube_id=document.querySelector('input[name=youtube_id]').value;
	
	querySelector(".title").innerhtml="title[1]";
	querySelector(".artist").innerhtml="artist[1]";
	
}
*/
// Initialize these as global variables
var player;
var currentTrack = 0;
var youtubeIDs;

function loadPlayer(ids) {
    youtubeIDs = ids;
    
    player = new YT.Player('bgmPlayer', {
        height: '0',
        width: '0',
        videoId: youtubeIDs[currentTrack],
        playerVars: {
			autoplay: 1,
            loop: 1,
			mute:0,
            playlist: youtubeIDs.slice(1).join(',')
        },
        events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
        }
    });
}

function onPlayerStateChange(event) {
    if (event.data === YT.PlayerState.ENDED) {
        nextMusic(); // Play the next track when one ends
    }
}

function onPlayerReady(event) {
    event.target.setVolume(100); // 볼륨을 100%로 설정
    // 자동 재생을 하지 않으므로 여기서 playVideo()를 호출하지 않습니다.
}

// '다음곡' 버튼을 눌렀을 때 호출되는 함수
function nextMusic() {
    if (currentTrack == youtubeIDs.length-1){
		currentTrack = 0;
	} else {
		currentTrack++;
	}
	player.loadVideoById(youtubeIDs[currentTrack]);
    player.playVideo(); // 다음 비디오 재생
}

// '이전곡' 버튼을 눌렀을 때 호출되는 함수
function prevMusic() {
    if (currentTrack == 0){
		currentTrack = youtubeIDs.length-1;
	} else {
		currentTrack--;
	}
	player.loadVideoById(youtubeIDs[currentTrack]);
    player.playVideo(); // 이전 비디오 재생
}

function playMusic() {
    player.playVideo();
}

function pauseMusic() {
    player.pauseVideo();
}