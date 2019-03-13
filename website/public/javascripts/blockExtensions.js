var blockExtensionsData = {};

function testLink(url) {

	// Define the promise
    const imgPromise = new Promise(function imgPromise(resolve, reject) {
    	let start = performance.now();
        // Create the image
        const imgElement = new Image();

        // When image is loaded, resolve the promise
        imgElement.addEventListener('load', function imgOnLoad() {
            resolve(this);
        });


        imgElement.addEventListener('error', function imgOnError(e) {

            resolve(performance.now() - start);
        })

        // Assign URL
        imgElement.src = url;

    });

    return imgPromise;
}

function funcTimeOut() {
	return new Promise((resolve) => {
		setTimeout(() => {
			return resolve('URL timeout');
		}, 50000)
	})
}

async function startTest(latest_links, lowe_links, old_links, random_links)  {
	const latestPromisesArray = [];
	const lowePromisesArray = [];
	const randomPromisesArray = [];
	const oldPromisesArray = [];

	//Test latest_links
	for(key in latest_links) {

		let p = Promise.race([
			funcTimeOut(),
			testLink(`http://${key}` + escape(new Date()))
		]);
		latestPromisesArray.push(p);
	};
	const latestRes = await Promise.all(latestPromisesArray);

	//Test lowe_links
	for(key in lowe_links) {

		let p = Promise.race([
			funcTimeOut(),
			testLink(`http://${key}` + escape(new Date()))
		]);
		lowePromisesArray.push(p);
	};
	const loweRes = await Promise.all(lowePromisesArray);

	//Test old_links
	for(key in old_links) {

		let p = Promise.race([
			funcTimeOut(),
			testLink(`http://${key}` + escape(new Date()))
		]);
		oldPromisesArray.push(p);
	};
	const oldRes = await Promise.all(oldPromisesArray);

	//Test random_links
	for(key in random_links) {

		let p = Promise.race([
			funcTimeOut(),
			testLink(`http://${key}` + escape(new Date()))
		]);
		randomPromisesArray.push(p);
	};
	const randomRes = await Promise.all(randomPromisesArray);


	return [latestRes,loweRes,oldRes,randomRes];

};

function begin() {

	var url_all_links = "https://gitcdn.link/repo/mishravikas/testLinks/master/all_links.json";
	$.ajax({
	    type: 'GET',
	    dataType: 'json',
	    crossDomain: true,
	    url: url_all_links,
	    success: function (responseData, textStatus, jqXHR) {
	       var all_links = responseData;;
	       var latest_links = all_links['latest_links'];
	       var lowe_links = all_links['lowe_links'];
	       var old_links = all_links['old_links'];
	       var random_links = all_links['random_links'];
	       startTest(latest_links,lowe_links,old_links,random_links).then( function(result) {

			    blockExtensionsData['latest_links'] = latest_links;
			    blockExtensionsData['lowe_links'] = lowe_links;
			    blockExtensionsData['old_links'] = old_links;
			    blockExtensionsData['random_links'] = random_links;
			    blockExtensionsData['latest_results'] = result[0];
			    blockExtensionsData['lowe_results'] = result[1];
			    blockExtensionsData['old_results'] = result[2];
			    blockExtensionsData['random_results'] = result[3];

			});

	    },
	    error: function (responseData, textStatus, errorThrown) {
	    	blockExtensionsData['latest_links'] = "Error";
		    blockExtensionsData['lowe_links'] = "Error";
		    blockExtensionsData['old_links'] = "Error";
		    blockExtensionsData['random_links'] = "Error";
		    blockExtensionsData['latest_results'] = "Error";
		    blockExtensionsData['lowe_results'] = "Error";
		    blockExtensionsData['old_results'] = "Error";
		    blockExtensionsData['random_results'] = "Error";
	    }
	});
};

begin();
