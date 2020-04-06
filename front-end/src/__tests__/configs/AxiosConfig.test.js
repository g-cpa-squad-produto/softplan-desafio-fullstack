import AxiosConfig from '../../configs/AxiosConfig';
import axios from 'axios';

describe('AxiosConfig', () => {

  it('Should call axios.create one time if instance dont exists', () => {
    const axiosCreate = jest.spyOn(axios, 'create');
    new AxiosConfig();

    expect(axiosCreate).toBeCalledTimes(1);
  });

  it('Should call axios.create only one time if instance exists', () => {
    const axiosCreate = jest.spyOn(axios, 'create');
    new AxiosConfig();
    new AxiosConfig();

    expect(axiosCreate).toBeCalledTimes(1);
  });
});
