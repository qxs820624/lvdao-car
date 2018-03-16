 	$(function() {
 		
	    // 百度地图API功能
		var map = new BMap.Map("allmap"); 
		/*var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});*/
		

		 function setMapEvent() {
	          map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
	          map.enableScrollWheelZoom();//启用地图滚轮放大缩小
	          map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
	          map.enableKeyboard();//启用键盘上下左右键移动地图
	          map.centerAndZoom(new BMap.Point(116.404, 39.915), 12);//创建地图
	      }
	     setMapEvent();

		$("#search").click(function(event) {
			//alert("aa");
			var x = document.getElementById("Lng").value;
			var y = document.getElementById("Lat").value;
			var Range = document.getElementById("range").value;
			var Range = parseInt(Range);  //转换数据类型
			var point= new BMap.Point(x, y);
			map.centerAndZoom(point, 12);
			var circle = new BMap.Circle(point,Range,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
	    	map.addOverlay(circle);  //添加圆覆盖物
			var area=document.getElementById("wordkey").value;
			var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);  //创建中心标注

			if( x !== "" && y !== ""){
				local.searchNearby(area, point,Range);
			}else{
				map.centerAndZoom(new BMap.Point(116.404, 39.915), 15);
				local.search(area);
			}
			
		});
		var options = {
			renderOptions: {
				map: map
			},
			onSearchComplete: function(results){
				// 判断状态是否正确
				if (local.getStatus() == BMAP_STATUS_SUCCESS){
					var s = [];
					for (var i = 0; i < results.getCurrentNumPois(); i ++){
						s.push(results.getPoi(i).title + ", " + results.getPoi(i).address+ " , " +results.getPoi(i).phoneNumber);
						$(".baidu_form tbody").append("<tr class=\"gaode_one\"><td>"+( i + 1 )+"</td><td class=\"name\"></td><td class=\"address\"></td><td class=\"tel\"></td></tr>");
						$(".baidu_form tbody tr").eq($(this).index()).children(".name").html(results.getPoi(i).title);
			        	$(".baidu_form tbody tr").eq($(this).index()).children(".address").html(results.getPoi(i).address);
			        	$(".baidu_form tbody tr").eq($(this).index()).children(".tel").html(results.getPoi(i).phoneNumber);
					}
					//document.getElementById("r-result").innerHTML = s.push(results.getPoi(i).title).join("");
				}
			}
		};
		var local = new BMap.LocalSearch(map, options);
		
 	});