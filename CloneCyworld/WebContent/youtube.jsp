<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.youtube.com/iframe_api"></script>
</head>
  <body>
    <div id="player"></div>
	<button onclick="stopVideo()">정지</button>
	<button onclick="nextVideo()">다음곡</button>
    <script>
     
      var player;
      function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
          height: '360',
          width: '640',
          videoId: 'CVxTT38_J4c',
         playerVars:{
        	 mute:1,
        	 autoplay:1,
        	 playlist:['CVxTT38_J4c', 'okVTSehE414']
         },
          events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
          }
        });
      }

      // 4. The API will call this function when the video player is ready.
      function onPlayerReady(event) {
        event.target.playVideo();
      }

      // 5. The API calls this function when the player's state changes.
      //    The function indicates that when playing a video (state=1),
      //    the player should play for six seconds and then stop.
      var done = false;
      function onPlayerStateChange(event) {
      
      }
      function stopVideo() {
        player.stopVideo();
      }
      
      function nextVideo(){
    	  player.nextVideo();
		}
    </script>
  </body>
</html>