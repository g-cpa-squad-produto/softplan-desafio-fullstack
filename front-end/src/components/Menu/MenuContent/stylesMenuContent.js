import styled from 'styled-components';
import { Link } from 'react-router-dom';
import colors from '../../../styles/colors';
import metrics from '../../../styles/metrics';

export const Container = styled.div`
  padding: ${metrics.basePadding / 2}px 0;
`;

export const Item = styled.div`
  border-bottom: 1px solid ${colors.light};
`;

export const StyledLink = styled(Link)`
  display: block;
  padding: ${metrics.basePadding / 2}px ${metrics.basePadding - 5}px;
  color: inherit;
  text-decoration: none;
  cursor: pointer;
  &:hover {
    color: inherit;
    text-decoration: none;
    background-color: ${colors.lighter};
  }
  &:focus {
    text-decoration: none;
  }
  &:visited {
    color: inherit;
  }
`;

export const UserInfo = styled.div`
  display: block;
  padding: ${metrics.basePadding / 2}px ${metrics.basePadding / 2}px;
  margin-bottom: ${metrics.baseMargin}px;
  color: inherit;
  text-decoration: none;
  border-bottom: 1px solid ${colors.light};
  text-overflow: ellipsis;
  overflow: hidden;
  svg {
    height: 40px !important;
    width: 40px !important;
  }
`;
