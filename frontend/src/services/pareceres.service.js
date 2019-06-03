import Config from '../commons/config';

const PareceresService = {
    save
};

const urlBase = `${Config.URL_API}/pareceres`;

function save(parecer) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(parecer)
    };
    return fetch(urlBase, requestOptions);
}

export default PareceresService;