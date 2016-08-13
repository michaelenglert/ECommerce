var _ = require('lodash');

module.exports = function(request, response, variables) {
    response.delay = _.random(variables.min, variables.max);
}