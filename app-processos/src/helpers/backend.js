export function configureBackend() {
    let realFetch = window.fetch;
    
    window.fetch = function (url, opts) {
        return new Promise((resolve, reject) => {
            realFetch(url, opts).then(response => resolve(response));
        });
    }
}