import styled from 'styled-components';
import colors from '../../styles/colors';
import metrics from '../../styles/metrics';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 70vh;
  padding: 0 ${metrics.basePadding}px;
  width: 100%;
`;

export const Form = styled.form`
  flex: 1;
  background: #fff;
  padding: ${metrics.basePadding}px;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 270px;
  max-width: 400px;

  p {
    color: ${colors.danger};
    margin-bottom: 15px;
    border: 1px solid ${colors.danger};
    padding: ${metrics.basePadding / 4}px 0;
    width: 100%;
    text-align: center;
  }
  input,
  select {
    height: 46px;
    margin-bottom: ${metrics.baseMargin + 5}px;
    padding: 0 ${metrics.basePadding}px;
    color: ${colors.dark};
    font-size: 15px;
    width: 100%;
    border: 1px solid ${colors.light};
    cursor: pointer;
    &::placeholder {
      color: ${colors.regular};
    }
  }
  button {
    color: ${colors.white};
    font-size: 16px;
    background: ${colors.sucess};
    height: 40px;
    border: 0;
    border-radius: ${metrics.baseRadius};
    width: 50%;
    cursor: pointer;
    transition: all ease-in-out 0.2s;
    &:hover {
      opacity: 0.6;
    }
  }
`;

export const Title = styled.h1`
  color: ${colors.dark};
  margin-bottom: ${metrics.baseMargin * 2}px;
`;
