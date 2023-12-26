// Initialize these as global variables
var player;
var currentTrack = 0;
var youtubeIDs;

function loadPlayer(ids) {
    youtubeIDs = ids;
    
    player = new YT.Player('bgmPlayer', {
        height: '300',
        width: '300',
        videoId: youtubeIDs[currentTrack],
        playerVars: {
			autoplay: 0,
            loop: 1,
			mute:1,
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