const webpack = require('webpack')

module.exports = {
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                $: 'jquery/dist/jquery.min.js',
                jQuery: 'jquery/dist/jquery.min.js',
                Popper: ['popper.js/dist/popper.min.js', 'default']
            })
        ]
    }
  };