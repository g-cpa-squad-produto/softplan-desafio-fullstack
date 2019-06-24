import styled from 'styled-components';

import colors from '../../styles/colors';
import metrics from '../../styles/metrics';

export const Container = styled.div`
  padding: ${metrics.basePadding / 2}px;
  position: absolute;
  left: 0;
  background: ${colors.regular};
  width: 100%;
`;

export const Title = styled.p`
  position: absolute;
  top: calc(50% - 8px);
  left: calc(50% - 95px);
  font-size: 16px;
  font-weight: bold;
  @media (min-width: 768px) {
    font-size: 20px;
    top: calc(50% - 10px);
    left: calc(50% - 150px);
  }
  @media (min-width: 1024px) {
    font-size: 24px;
    top: calc(50% - 12px);
  }
`;
