//header -> x-appd-demo=google:0
//url -> ?x-appd-demo=google:1;

var http = require('http');
var url = require('url');
var _ = require('lodash');
var routes = require('./config/routes.js');
var behaviors = require('./behaviors');

http.createServer(function (req, res) {

    var urlComponents = url.parse(req.url, true);
    var info = {
        defaultRoute : true,
        behavior : [],
        errors : [],
        requestTriggers : []
    };

    var triggerString = (!_.isUndefined(urlComponents.query['x-appd-demo'])) ? urlComponents.query['x-appd-demo'] : '';
    if (!_.isUndefined(req.headers['x-appd-demo'])) {
        triggerString += req.headers['x-appd-demo'];
    }

    triggerString.split(';').forEach(function(value) {
        var b = value.split(":");
        b[1] = b[1] ? b[1] : 0;
        info.requestTriggers.push({name : b[0], value : b[1]});
    });

    routes.forEach(function(route) {
        if (route.url === urlComponents.pathname) {
            info.defaultRoute = false;
            info.routeUrl = route.url;
            info.requestTriggers.forEach(function(trigger) {
                route.behavior.forEach(function(behavior) {
                    if (trigger.name === behavior.name) {
                        if (behavior.vars[trigger.value]) {
                            info.behavior.push(behavior);
                            behaviors[behavior.type](req, res, behavior.vars[trigger.value]);
                        } else {
                            info.errors.push('no matching route for ' + trigger.name + ' with value of ' + trigger.value);
                        }
                    }

                });
            });
        }
    });
    sendResponse(res, info);
}).listen(3000);

var sendResponse = function(response, info) {
    var delay = (response.delay) ? response.delay : 0;
    info.delay = delay;

    setTimeout(function() {
        response.end(JSON.stringify(info));
    }, delay);
}
