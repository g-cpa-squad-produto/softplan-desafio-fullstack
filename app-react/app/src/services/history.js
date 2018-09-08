import { createBrowserHistory } from 'history';

export const history = createBrowserHistory({
    basename: '',
    forceRefresh: false,
    keyLength: 6
});

const unlisten = history.listen((location, action) => {
    console.log(action, location.pathname, location.state)
});