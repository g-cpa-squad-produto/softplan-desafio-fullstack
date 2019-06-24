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

export const List = styled.ul`
  display: inline-block;
  width: 100%;
  padding: 0 ${metrics.basePadding}px;
  @media (min-width: 768px) {
    padding: 0 ${metrics.basePadding * 3}px;
  }
  @media (min-width: 1024px) {
    padding: 0 ${metrics.basePadding * 6}px;
    padding-top: ${metrics.basePadding / 2}px;
  }
  @media (min-width: 1366px) {
    padding: 0 ${metrics.basePadding * 9}px;
    padding-top: ${metrics.basePadding / 2}px;
  }
`;

export const ListItems = styled.li`
  display: flex;
  border-bottom: 1px solid ${colors.regular};
  width: 100%;
  padding: 0 ${metrics.basePadding}px;
  border: 1px solid ${colors.regular};
  &:nth-child(odd) {
    background-color: ${colors.light};
  }
`;

export const ListItem = styled.p`
  width: 100%;
  text-overflow: ellipsis;
  overflow: hidden;
  font-size: 12px;
  @media (min-width: 768px) {
    font-size: 14px;
  }
  @media (min-width: 1024) {
    font-size: 16px;
  }
  @media (min-width: 1366px) {
    font-size: 18px;
  }
`;

export const Button = styled.button`
  border: 0;
  background: transparent;
  margin-left: ${metrics.basePadding / 2}px;
  cursor: pointer;
  &:nth-child(n + 2) {
    margin-left: ${metrics.basePadding / 2}px;
  }
  &:hover {
    background: ${colors.whiteTransparent};
  }
  transition: all ease-in-out 0.2s;
  &:hover {
    opacity: 0.6;
  }
`;

export const ListHead = styled.li`
  display: flex;
  border-bottom: 1px solid ${colors.regular};
  width: 100%;
  padding: 0 ${metrics.basePadding}px;
  border: 1px solid ${colors.regular};
  font-weight: bold;
  background-color: ${colors.regular};
`;

export const ListHeadItem = styled.p`
  width: 100%;
  font-size: 14px;
  @media (min-width: 768px) {
    font-size: 16px;
  }
  @media (min-width: 1024) {
    font-size: 18px;
  }
  @media (min-width: 1366px) {
    font-size: 20px;
  }
`;

export const HeadItem = styled.span`
  padding: 0 ${metrics.basePadding / 4}px;
`;
