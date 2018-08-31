'use strict';

var webpack = require('webpack');
var autoprefixer = require('autoprefixer');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var UglifyJSPlugin = require('uglifyjs-webpack-plugin');

/* Executing tests */
var ENV = process.env.npm_lifecycle_event;
var isTest = ENV === 'test' || ENV === 'test-watch';
/**/

module.exports = function (env) {

    var configFile = require('./environments/' + env.NODE_ENV);
    console.log('ENV: ', env);
    console.log('CONFIG FILE: ', configFile);

    var isProd = env.NODE_ENV === 'prod';
    var isLocal = env.NODE_ENV === 'local';

    var config = {};

    config.entry = isTest ? void 0 : {
        app: './src/app/app.module.js'
    };

    config.output = isTest ? {} : {
        path: __dirname + '/dist',
        publicPath: isProd ? '/' : 'http://localhost:9001/',
        filename: isProd ? 'js/[name].[hash].js' : '[name].bundle.js',
        chunkFilename: isProd ? 'js/[name].[hash].js' : '[name].bundle.js'
    };

    if (isTest) {
        config.devtool = 'inline-source-map';
    }
    else if (isProd) {
        config.devtool = 'source-map';
    }
    else {
        config.devtool = 'eval-source-map';
    }

    config.module = {
        rules: [{
            test: /\.js$/,
            loader: 'babel-loader',
            exclude: /node_modules/
        }, {
            test: /\.css$/,
            loader: isTest ? 'null-loader' : ExtractTextPlugin.extract({
                fallbackLoader: 'style-loader',
                loader: [
                    {loader: 'css-loader', query: {sourceMap: true}},
                    {loader: 'postcss-loader'}
                ],
            })
        }, {
            test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/,
            loader: 'file-loader'
        }, {
            test: /\.html$/,
            loader: 'raw-loader'
        }]
    };

    if (isTest) {
        config.module.rules.push({
            enforce: 'pre',
            test: /\.js$/,
            exclude: [
                /node_modules/,
                /\.spec\.js$/
            ],
            loader: 'istanbul-instrumenter-loader',
            query: {
                esModules: true
            }
        })
    }

    config.plugins = [
        new webpack.LoaderOptionsPlugin({
            test: /\.scss$/i,
            options: {
                postcss: {
                    plugins: [autoprefixer]
                }
            }
        })
    ];


    var defineValues = {};
    Object.keys(configFile).forEach(function(key) {
        defineValues[key] = JSON.stringify(configFile[key]);
    });

    config.plugins.push(new webpack.DefinePlugin(defineValues));

    if (!isTest) {
        config.plugins.push(
            new HtmlWebpackPlugin({
                template: './src/public/index.html',
                inject: 'body'
            }),
            new ExtractTextPlugin({filename: 'css/[name].css', disable: !isProd, allChunks: true})
        )
    }

    if (isProd) {
        config.plugins.push(
            new webpack.NoEmitOnErrorsPlugin(),

            new UglifyJSPlugin(),

            new CopyWebpackPlugin([{
                from: __dirname + '/src/public'
            }])
        )
    }

    if (isLocal) {
        config.devServer = {
            contentBase: './src/public',
            stats: 'minimal',
            port: 9001
        };
    }

    return config;
};
