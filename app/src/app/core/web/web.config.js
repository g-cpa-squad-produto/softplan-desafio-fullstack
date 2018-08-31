export default function webConfig($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}

webConfig.$inject = [
    '$qProvider'
];
