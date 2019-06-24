import styled from 'styled-components';
import colors from '../../styles/colors';
import metrics from '../../styles/metrics';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background: white;
  padding: ${metrics.basePadding / 2}px 0;
  width: 100%;
  margin: ${metrics.basePadding / 2}px ${metrics.basePadding}px;
  margin-top: ${metrics.basePadding * 2 + 7}px;
  @media (min-width: 768px) {
    margin: ${metrics.basePadding / 2}px ${metrics.basePadding * 3}px;
    margin-top: ${metrics.basePadding * 2 + 7}px;
  }
`;

export const Form = styled.form`
  min-width: 270px;
  max-width: 400px;
  padding: ${metrics.basePadding}px;

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
    display: block;
    color: ${colors.white};
    font-size: 16px;
    background: ${colors.sucess};
    height: 40px;
    border: 0;
    border-radius: ${metrics.baseRadius}px;
    width: 50%;
    cursor: pointer;
    transition: all ease-in-out 0.2s;
    margin: 0 auto;
    &:hover {
      opacity: 0.6;
    }
  }
`;

export const Error = styled.p`
  color: ${colors.danger};
  margin-bottom: 15px;
  border: 1px solid ${colors.danger};
  padding: ${metrics.basePadding / 4}px 0;
  width: 100%;
  text-align: center;
`;

export const Success = styled.p`
  color: ${colors.sucess};
  margin-bottom: 15px;
  border: 1px solid ${colors.sucess};
  padding: ${metrics.basePadding / 4}px 0;
  width: 100%;
  text-align: center;
`;
