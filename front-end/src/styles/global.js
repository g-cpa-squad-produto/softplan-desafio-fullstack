import { createGlobalStyle } from 'styled-components';
import colors from './colors';

const GlobalStyle = createGlobalStyle`
  *{
    margin:  0;
    padding:0;
    box-sizing: border-box;
    outline: 0;
  }

  body,html,#root{
    background: ${colors.lighter};
    text-rendering: optimizeLegibility !important;
    -webkit-font-smoothing: antialiased !important;
    font-family: Arial, sans-serif;
    width: 100%;
    height: 100%;
  }

  #root{
    display: flex;
    justify-content: center;
  }

  .cheeseburger-menu + div{
    z-index: 1;
    cursor: pointer;
  }
  .cheeseburger-menu-outer{
    margin-top: 44px !important;
  }
  .react-confirm-alert-overlay{
    background: ${colors.darkTransparent} !important;
  }
`;

export default GlobalStyle;
