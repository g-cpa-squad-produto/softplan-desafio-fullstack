import _ from 'lodash';

export function validateField(name, state, requiredFields) {
    if (!_.includes(requiredFields, name)) {
        return;
    }
    const invalids = state.invalids;
    const isValid = Boolean(state[name]);
    if (isValid) {
        _.remove(invalids, (field) => field === name);
    } else if (!_.includes(invalids, name)) {
        invalids.push(name);
    }
    return {
        formValid: !invalids.length,
        invalids
    };
}

export function validateForm(state, requiredFields) {
    const invalids = _.chain(requiredFields)
        .filter((field) => !state[field])
        .value();

    return {
        formValid: !invalids.length,
        invalids
    };
}
