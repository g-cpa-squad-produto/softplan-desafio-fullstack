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

import { init, create, update, remove } from "./processoActions";
import { getFinalizadores } from "../usuario/usuarioActions";

import Form from "./processoForm";
import List from "./processoList";

class Processo extends Component {
  componentWillMount() {
    this.props.init();
    this.props.getFinalizadores();
  }

  render() {
    return (
      <div>
        <ContentHeader title="Processos" subTitle="Cadastro" />
        <Content>
          <Tabs>
            <TabsHeader>
              <TabHeader label="Listar" icon="bars" target="tabList" />
              <TabHeader label="Incluir" icon="plus" target="tabCreate" />
              <TabHeader label="Alterar" icon="pencil" target="tabUpdate" />
              <TabHeader label="Excluir" icon="trash-o" target="tabDelete" />
            </TabsHeader>
            <TabsContent>
              <TabContent id="tabList">
                <List />
              </TabContent>
              <TabContent id="tabCreate">
                <Form
                  onSubmit={this.props.create}
                  listAprovadores={this.props.listAprovadores}
                  submitLabel="Incluir"
                  submitClass="primary"
                />
              </TabContent>
              <TabContent id="tabUpdate">
                <Form
                  onSubmit={this.props.update}
                  submitLabel="Alterar"
                  submitClass="info"
                />
              </TabContent>
              <TabContent id="tabDelete">
                <Form
                  onSubmit={this.props.remove}
                  readOnly={true}
                  submitLabel="Excluir"
                  submitClass="danger"
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
      create,
      update,
      remove,
      getFinalizadores
    },
    dispatch
  );
export default connect(
  null,
  mapDispatchToProps
)(Processo);
