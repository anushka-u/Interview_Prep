- **AI-ENGINEERING** -- https://ai.studybydoing.in/py1-basics
- **VIBE ENGINES** -- https://vibeengines.com/ai-system-design/conversational-ai-system-design
- **CHAI CODE** -- https://labs.chaicode.com/?utm_source=chatgpt.com
- **SCALE DOJO** -- https://scaledojo.dev/challenges?utm_source=chatgpt.com

# AI Engineering & AI System Design – Learning Roadmap

## Objective

Build the ability to independently understand, implement, test and design AI-powered applications using Python.

**Current Level:** Beginner (Python knowledge)  
**Target Level:** Intermediate–Advanced AI Application Engineer  
**Learning Style:** Concept → Implementation → Coding Exercise → Machine Coding → System Design

---

# LEVEL 1 – AI Fundamentals

**Goal:** Understand what AI is and how modern AI applications work.

### Topics

- [ ] What is Artificial Intelligence (AI)?
- [ ] Artificial Intelligence vs Machine Learning vs Deep Learning.
- [ ] What is Generative AI?
- [ ] What is an LLM (Large Language Model)?
- [ ] Traditional programming vs AI-based applications.
- [ ] How does an LLM work at a high level?
- [ ] What are pretrained models?
- [ ] Open-source vs proprietary models.
- [ ] Local models vs cloud-hosted models.
- [ ] What is inference?
- [ ] What are model parameters?

### Practical Implementation

- [ ] Run a local LLM using Ollama.
- [ ] Send input to an LLM using Python.
- [ ] Understand model request and response.
- [ ] Experiment with different inputs and observe responses.

### Coding Exercises

- [ ] Build a simple Python program that interacts with an LLM.
- [ ] Build a basic question-answering program.
- [ ] Handle invalid inputs and model failures.

**Completion Goal:** Understand how an AI application communicates with an LLM.

---

# LEVEL 2 – LLM Fundamentals

**Goal:** Understand how LLMs process text and generate responses.

### Topics

- [ ] What are tokens?
- [ ] Tokenization.
- [ ] Token embeddings.
- [ ] Context window.
- [ ] Model parameters.
- [ ] Temperature.
- [ ] Top-P sampling.
- [ ] Hallucinations.
- [ ] System prompts.
- [ ] User prompts.
- [ ] Inference latency.
- [ ] Model limitations.

### Practical Implementation

- [ ] Tokenize text using a tokenizer.
- [ ] Calculate token counts.
- [ ] Experiment with temperature.
- [ ] Compare model responses with different prompts.
- [ ] Observe context window limitations.

### Coding Exercises

- [ ] Implement a basic token counter.
- [ ] Build a prompt configuration manager.
- [ ] Implement context length validation.
- [ ] Create a program that compares responses from different prompts.

**Completion Goal:** Understand the basic mechanics and limitations of LLMs.

---

# LEVEL 3 – Prompt Engineering

**Goal:** Learn how to control and structure LLM responses.

### Topics

- [ ] Zero-shot prompting.
- [ ] Few-shot prompting.
- [ ] Role-based prompting.
- [ ] Prompt templates.
- [ ] Structured output (JSON).
- [ ] Output validation.
- [ ] Prompt chaining.
- [ ] Context engineering.
- [ ] Prompt injection basics.

### Practical Implementation

- [ ] Create reusable prompt templates.
- [ ] Generate structured JSON responses.
- [ ] Extract information from unstructured text.
- [ ] Validate model-generated output.

### Coding Exercises

- [ ] Build a resume information extractor.
- [ ] Build a text classification system.
- [ ] Implement a structured JSON response validator.
- [ ] Build a prompt manager supporting multiple templates.

**Completion Goal:** Write reliable prompts and consume structured LLM responses.

---

# LEVEL 4 – LLM Application Development

**Goal:** Integrate LLMs into real Python applications.

### Topics

- [ ] LLM APIs.
- [ ] Ollama architecture.
- [ ] Model requests and responses.
- [ ] Python LLM integration.
- [ ] Conversation history.
- [ ] Chat memory.
- [ ] Streaming responses.
- [ ] Asynchronous LLM calls.
- [ ] Timeouts and retries.
- [ ] Exception handling.
- [ ] FastAPI integration.

### Practical Implementation

- [ ] Integrate Ollama with Python.
- [ ] Create reusable LLM client classes.
- [ ] Maintain conversation history.
- [ ] Expose an LLM through REST APIs.
- [ ] Implement streaming responses.

### Coding Exercises

- [ ] Implement an LLM client.
- [ ] Implement retry and timeout handling.
- [ ] Implement conversation history management.
- [ ] Implement a streaming response handler.
- [ ] Implement an API endpoint for chatbot interaction.

### Machine Coding

**Project 1: Local AI Chatbot**

Requirements:
- Accept user questions.
- Generate responses using a local LLM.
- Maintain conversation history.
- Support multiple conversations.
- Handle model failures.
- Provide a REST API.

**Completion Goal:** Independently build a working LLM-powered backend application.

---

# LEVEL 5 – Embeddings and Vector Databases

**Goal:** Understand semantic search and how AI applications retrieve relevant information.

### Topics

- [ ] What are embeddings?
- [ ] How text is converted into vectors.
- [ ] Vector dimensions.
- [ ] Vector similarity.
- [ ] Cosine similarity.
- [ ] Semantic search.
- [ ] Vector indexing.
- [ ] Vector databases.
- [ ] Similarity search.
- [ ] Top-K retrieval.
- [ ] Metadata filtering.
- [ ] Approximate Nearest Neighbour (ANN).

### Technologies

- NumPy
- FAISS
- ChromaDB
- Pinecone (conceptually)

### Practical Implementation

- [ ] Generate embeddings.
- [ ] Calculate cosine similarity.
- [ ] Compare semantically similar sentences.
- [ ] Store embeddings in a vector database.
- [ ] Retrieve similar documents.
- [ ] Implement metadata-based filtering.

### Coding Exercises

- [ ] Implement cosine similarity from scratch.
- [ ] Implement a basic vector search.
- [ ] Implement Top-K retrieval.
- [ ] Implement a document similarity checker.
- [ ] Implement a semantic search function.

### Machine Coding

**Project 2: Semantic Search Engine**

Requirements:
- Accept multiple documents.
- Generate document embeddings.
- Store vectors.
- Accept a search query.
- Retrieve semantically similar documents.
- Return the most relevant results.

**Completion Goal:** Understand and implement vector-based information retrieval.

---

# LEVEL 6 – Retrieval-Augmented Generation (RAG)

**Goal:** Build AI applications that answer questions using external knowledge.

### Topics

- [ ] What is RAG?
- [ ] Why do we need RAG?
- [ ] RAG architecture.
- [ ] Document ingestion.
- [ ] Document parsing.
- [ ] Text chunking.
- [ ] Chunk size and overlap.
- [ ] Embedding generation.
- [ ] Vector storage.
- [ ] Information retrieval.
- [ ] Context injection.
- [ ] Response generation.
- [ ] Reranking.
- [ ] Hybrid search.
- [ ] Retrieval quality.
- [ ] RAG limitations.

### Practical Implementation

- [ ] Load PDF documents.
- [ ] Extract text.
- [ ] Split documents into chunks.
- [ ] Generate embeddings.
- [ ] Store embeddings in a vector database.
- [ ] Retrieve relevant chunks.
- [ ] Pass retrieved context to an LLM.
- [ ] Generate answers with document references.

### Coding Exercises

- [ ] Implement a document chunker.
- [ ] Implement chunk overlap.
- [ ] Implement a document retrieval function.
- [ ] Implement context construction.
- [ ] Implement a RAG pipeline.
- [ ] Handle questions for which no relevant document exists.

### Machine Coding

**Project 3: Chat with PDF**

Requirements:
- Upload PDF documents.
- Extract and process content.
- Generate and store embeddings.
- Accept natural-language questions.
- Retrieve relevant information.
- Generate context-aware answers.
- Include document references.
- Handle irrelevant questions.

**Completion Goal:** Independently implement a complete RAG application.

---

# LEVEL 7 – AI Agents and Tool Calling

**Goal:** Understand how AI applications interact with external systems and execute actions.

### Topics

- [ ] What is an AI agent?
- [ ] LLM vs AI agent.
- [ ] What is function calling?
- [ ] What is tool calling?
- [ ] Tool execution.
- [ ] Tool selection.
- [ ] Agent planning.
- [ ] Task decomposition.
- [ ] Agent memory.
- [ ] Single-agent architecture.
- [ ] Multi-agent architecture.
- [ ] Agent workflows.
- [ ] Human-in-the-loop.
- [ ] Model Context Protocol (MCP).

### Practical Implementation

- [ ] Create a calculator tool.
- [ ] Create a search tool.
- [ ] Connect an LLM to external Python functions.
- [ ] Connect an agent to REST APIs.
- [ ] Implement tool execution.
- [ ] Handle tool execution failures.

### Coding Exercises

- [ ] Implement a tool registry.
- [ ] Implement a tool executor.
- [ ] Implement tool selection.
- [ ] Implement an agent that calls multiple tools.
- [ ] Implement an agent with conversation memory.
- [ ] Implement human approval before sensitive operations.

### Machine Coding

**Project 4: AI Research Assistant**

Requirements:
- Accept a research question.
- Identify the required tools.
- Search for relevant information.
- Execute multiple tool calls.
- Summarize collected information.
- Maintain conversation context.
- Handle tool failures.

**Completion Goal:** Independently build an AI application capable of executing multi-step tasks.

---

# LEVEL 8 – AI Evaluation and Reliability

**Goal:** Understand how to test and improve AI application quality.

### Topics

- [ ] Why AI testing is different from traditional software testing.
- [ ] Response accuracy.
- [ ] Relevance.
- [ ] Faithfulness.
- [ ] Hallucination detection.
- [ ] Retrieval evaluation.
- [ ] RAG evaluation.
- [ ] LLM-as-a-judge.
- [ ] Evaluation datasets.
- [ ] Regression testing.
- [ ] Human feedback.
- [ ] Response consistency.

### Practical Implementation

- [ ] Create test datasets.
- [ ] Compare expected and generated responses.
- [ ] Evaluate retrieved documents.
- [ ] Test hallucination scenarios.
- [ ] Track changes in prompt quality.

### Coding Exercises

- [ ] Implement an AI response evaluator.
- [ ] Implement a retrieval quality checker.
- [ ] Build a basic automated evaluation pipeline.
- [ ] Create regression tests for prompts.

**Completion Goal:** Test AI applications systematically rather than relying on manual observation.

---

# LEVEL 9 – AI Security and Guardrails

**Goal:** Build secure AI applications.

### Topics

- [ ] Prompt injection.
- [ ] Sensitive information leakage.
- [ ] Personally Identifiable Information (PII).
- [ ] Input validation.
- [ ] Output validation.
- [ ] Access control.
- [ ] Authorization for tool calls.
- [ ] Rate limiting.
- [ ] Content filtering.
- [ ] Human approval.
- [ ] Secure document retrieval.
- [ ] Tenant-level data isolation.

### Practical Implementation

- [ ] Validate user inputs.
- [ ] Protect sensitive information.
- [ ] Restrict unauthorized tool execution.
- [ ] Validate generated JSON.
- [ ] Implement access control for an AI assistant.

### Coding Exercises

- [ ] Implement a basic PII detection layer.
- [ ] Implement tool permission checks.
- [ ] Implement an input guardrail.
- [ ] Implement a secure document retrieval flow.

**Completion Goal:** Understand and mitigate AI-specific application security risks.

---

# LEVEL 10 – Production AI Engineering

**Goal:** Understand how to operate AI applications in production.

### Topics

- [ ] LLM gateway.
- [ ] Model selection and routing.
- [ ] Multiple model providers.
- [ ] Model fallback.
- [ ] Load balancing.
- [ ] Response caching.
- [ ] Semantic caching.
- [ ] Token cost optimization.
- [ ] Rate limiting.
- [ ] Asynchronous processing.
- [ ] Queue-based processing.
- [ ] Streaming architecture.
- [ ] Latency optimization.
- [ ] Horizontal scaling.
- [ ] Logging and tracing.
- [ ] AI observability.
- [ ] Model monitoring.
- [ ] Deployment.
- [ ] LLMOps.

### Practical Implementation

- [ ] Create a reusable model gateway.
- [ ] Implement model fallback.
- [ ] Implement caching.
- [ ] Track token consumption.
- [ ] Add application logging.
- [ ] Implement request tracing.
- [ ] Deploy a Python AI application.

### Coding Exercises

- [ ] Implement a model router.
- [ ] Implement a response cache.
- [ ] Implement token usage tracking.
- [ ] Implement rate limiting.
- [ ] Implement fallback between models.
- [ ] Implement an asynchronous document processing pipeline.

**Completion Goal:** Understand the engineering requirements of production-ready AI systems.

---

# LEVEL 11 – AI System Design

**Goal:** Independently design complete AI-powered systems.

### Design Fundamentals

- [ ] AI application requirements gathering.
- [ ] Functional and non-functional requirements.
- [ ] Component identification.
- [ ] AI architecture diagrams.
- [ ] API design.
- [ ] Database selection.
- [ ] Vector database selection.
- [ ] Model selection.
- [ ] Data flow design.
- [ ] Component communication.
- [ ] Failure handling.
- [ ] Security considerations.
- [ ] Scalability.
- [ ] Latency and cost trade-offs.
- [ ] Reliability and observability.

### System Design Problems

1. [ ] Design a ChatGPT-like application.
2. [ ] Design a PDF question-answering system.
3. [ ] Design a semantic search engine.
4. [ ] Design an AI customer support platform.
5. [ ] Design an AI code review assistant.
6. [ ] Design an AI research agent.
7. [ ] Design a multi-agent system.
8. [ ] Design an enterprise RAG platform.
9. [ ] Design a scalable LLM serving infrastructure.
10. [ ] Design an enterprise AI platform.

### Completion Goal

Independently gather requirements, draw architecture, identify components, explain trade-offs and design scalable AI applications.

---

# ADVANCED TOPICS – Optional Specialisation

These topics can be explored after completing the core application engineering roadmap.

- [ ] Transformer architecture.
- [ ] Attention mechanism.
- [ ] Neural network fundamentals.
- [ ] Fine-tuning.
- [ ] LoRA and PEFT.
- [ ] Quantization.
- [ ] GPU inference.
- [ ] Model serving.
- [ ] Multimodal AI.
- [ ] Speech-to-text and text-to-speech.
- [ ] Advanced multi-agent orchestration.

---

# FINAL PROJECT PORTFOLIO

| Project | Main concepts |
|---|---|
| 1. Local AI Chatbot | LLM integration, prompts, memory |
| 2. Resume Analyzer | Structured output, prompting |
| 3. Semantic Search Engine | Embeddings, vector search |
| 4. PDF Chatbot | RAG |
| 5. AI Research Assistant | Agents, tool calling |
| 6. AI Code Review Agent | Agents, evaluation, code analysis |
| 7. AI Banking Assistant | RAG, APIs, security |
| 8. Enterprise AI Platform | Production architecture, scaling, monitoring |

---

# LEARNING METHODOLOGY

Every major topic will follow this progression:

1. **Understand:** Learn the concept and its purpose.
2. **Explore:** See a small working example.
3. **Implement:** Write the implementation in Python.
4. **Practise:** Solve coding exercises independently.
5. **Test:** Handle edge cases and failures.
6. **Machine Code:** Build a complete application.
7. **Design:** Design the architecture independently.
8. **Revise:** Maintain definitions, examples and interview questions.

### Learning Rules

- No long tutorial-based learning.
- Understand the fundamentals before using frameworks.
- Prefer implementing underlying logic before using ready-made libraries.
- Use Python and Ollama for local experimentation.
- Use automated tests wherever practical.
- Don't move forward just to finish the syllabus.
- Every major phase must produce working code.

**FINAL OBJECTIVE:**

Become an engineer who can independently approach an AI problem, identify the required components, implement the solution, test it, and design a production-ready AI system.