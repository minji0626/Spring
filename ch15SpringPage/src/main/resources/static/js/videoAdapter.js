window.onload=function(){
	const youtube = 'https://youtu.be/';//유튜브 주소1
	const youtube2 = 'https://www.youtube.com/watch?v=';//유튜브 주소2
	const videoArray = [];
	let link_url;
	const oembeds = document.getElementsByTagName('oembed');
	for(let i=0;i<oembeds.length;i++){
		let url = oembeds[i].getAttribute('url');
		if(url.includes(youtube)){
			link_url = url.substring(youtube.length);
		}else{
			link_url = url.substring(youtube2.length);
		}
		let output = '';
		output += '<div style="position: relative; padding-bottom: 100%; height: 0; padding-bottom: 56.2493%;">';
		output += '<iframe src="https://www.youtube.com/embed/'+link_url+'"';
		output += ' style="position: absolute; width: 100%; height: 100%; top: 0; left: 0;"';
		output += ' frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>';
		output += '</iframe>';
		output += '</div>';
		videoArray.push(output);
	}  
	if(videoArray.length>0){
		let media = document.getElementsByClassName('media');
		for(let i=0;i<media.length;i++){
			media[i].innerHTML=videoArray[i];
		}
	}
};