import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import ContentHeader from "../components/ContentHeader";
import Content from "../components/Content";
import Tabs from "../common/tab/tabs";
import TabsHeader from "../common/tab/tabsHeader";
import TabsContent from "../common/tab/tabsContent";
import TabHeader from "../common/tab/tabHeader";
import TabContent from "../common/tab/tabContent";

import { init, create } from "./parecerActions";

import Form from "./parecerForm";
import List from "./parecerList";

class Parecer extends Component {
  componentWillMount() {
    this.props.init();
  }

  render() {
    return (
      <div>
        <ContentHeader title="Parecer" subTitle="Processos" />
        <Content>
          <Tabs>
            <TabsHeader>
              <TabHeader label="Listar" icon="bars" target="tabList" />
              <TabHeader label="Parecer" icon="plus" target="tabCreate" />
            </TabsHeader>
            <TabsContent>
              <TabContent id="tabList">
                <List />
              </TabContent>
              <TabContent id="tabUpdate">
                <Form
                  onSubmit={this.props.create}
                  submitLabel="Incluir Parecer"
                  submitClass="info"
                  readOnly={true}
                />
              </TabContent>
            </TabsContent>
          </Tabs>
        </Content>
      </div>
    );
  }
}

const mapDispatchToProps = dispatch =>
  bindActionCreators(
    {
      init,
      create
    },
    dispatch
  );
export default connect(
  null,
  mapDispatchToProps
)(Parecer);
