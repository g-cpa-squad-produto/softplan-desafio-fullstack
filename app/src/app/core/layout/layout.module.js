import angular from 'angular';
import ngAnimate from 'angular-animate';
import ngAria from 'angular-aria';
import ngMaterial from 'angular-material';
import ngMessages from 'angular-messages';
import angularInputMasks from 'angular-input-masks/br';
// import mdDataTable from 'angular-material-data-table';
import idfBrFilters from 'angular-br-filters';
import mdSelectData from 'md-select-data';

import materialThemeConfig from './material-theme.config';
import materialIconsConfig from './material-icons.config';

import 'angular-material-data-table/dist/md-data-table.min.css'

const MODULE_NAME = 'chl.core.layout';

angular.module(MODULE_NAME, [
    ngAnimate,
    ngAria,
    ngMaterial,
    ngMessages,
    angularInputMasks,
    // mdDataTable,
    idfBrFilters,
    mdSelectData
])
    .config(materialThemeConfig)
    .config(materialIconsConfig);

export default MODULE_NAME;